package com.icfcc.db.plugin;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.*;

/**
 * Mysql分頁插件
 * Created by WangHuanyu on 2015/11/5.
 */
@Intercepts(
        //Signature定义被拦截的接口方法，可以有一个或多个。
        @Signature(
                //拦截的接口类型，支持 Executor,ParameterHandler,ResultSetHandler,StatementHandler
                //这里以Executor为例
                type = Executor.class,
                //Executor中的方法名
                method = "query",
                //Executor中query方法的参数类型
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ))
public class ExamplePagePlugin implements Interceptor {

    //分页的id后缀
    String SUFFIX_PAGE = "_PageHelper";
    //count查询的id后缀
    String SUFFIX_COUNT = SUFFIX_PAGE + "_Count";
    //第一个分页参数
    String PAGEPARAMETER_FIRST = "First" + SUFFIX_PAGE;
    //第二个分页参数
    String PAGEPARAMETER_SECOND = "Second" + SUFFIX_PAGE;

    String PROVIDER_OBJECT = "_provider_object";
    //存储原始的参数
    String ORIGINAL_PARAMETER_OBJECT = "_ORIGINAL_PARAMETER_OBJECT";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //我们可以在这里做一些扩展工作，但一定要在了解mybatis运行原理之后才能开发出你所期望的效果。
        System.out.println("example plugin ..." + invocation);
        final Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        MetaObject msObject = SystemMetaObject.forObject(ms);
        BoundSql boundSql = ms.getBoundSql(args[1]);
        SqlSource sqlSource = ms.getSqlSource();
        SqlSource tempSqlSource = sqlSource;
        SqlSource pageSqlSource;
        //實例中只演示RowSqlSource
        if (tempSqlSource instanceof RawSqlSource) {
            pageSqlSource = new PageRawSqlSource((RawSqlSource) tempSqlSource);
        } else {
            throw new RuntimeException("无法处理该类型[" + sqlSource.getClass() + "]的SqlSource");
        }
        msObject.setValue("sqlSource", pageSqlSource);
        //添加分頁參數
        args[1] = setPageParameter(ms, args[1], boundSql, 6, 5);
        //执行分页查询
        //因为mybatis的使用责任链方式，这里一定要显示的调用proceed方法便调用能传递下去。
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        //判断是否是本拦截器需要拦截的接口类型，如果是增加代理
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        //如果不是返回源对象。
        else {
            return target;
        }
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public Map setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, int startrow, int pagesize) {
        Map paramMap = processParameter(ms, parameterObject, boundSql);
        paramMap.put(PAGEPARAMETER_FIRST, startrow);
        paramMap.put(PAGEPARAMETER_SECOND, pagesize);
        return paramMap;
    }

    public Map<String, Object> processParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql) {
        Map<String, Object> paramMap = null;
        if (parameterObject == null) {
            paramMap = new HashMap<String, Object>();
        } else if (parameterObject instanceof Map) {
            //解决不可变Map的情况
            paramMap = new HashMap<String, Object>();
            paramMap.putAll((Map<String, Object>) parameterObject);
        } else {
            paramMap = new HashMap<String, Object>();
            //动态sql时的判断条件不会出现在ParameterMapping中，但是必须有，所以这里需要收集所有的getter属性
            //TypeHandlerRegistry可以直接处理的会作为一个直接使用的对象进行处理
            boolean hasTypeHandler = ms.getConfiguration().getTypeHandlerRegistry().hasTypeHandler(parameterObject.getClass());
            MetaObject metaObject = SystemMetaObject.forObject(parameterObject);
            if (!hasTypeHandler) {
                for (String name : metaObject.getGetterNames()) {
                    paramMap.put(name, metaObject.getValue(name));
                }
            }
            //下面这段方法，主要解决一个常见类型的参数时的问题
            if (boundSql.getParameterMappings() != null && boundSql.getParameterMappings().size() > 0) {
                for (ParameterMapping parameterMapping : boundSql.getParameterMappings()) {
                    String name = parameterMapping.getProperty();
                    if (!name.equals(PAGEPARAMETER_FIRST)
                            && !name.equals(PAGEPARAMETER_SECOND)
                            && paramMap.get(name) == null) {
                        if (hasTypeHandler
                                || parameterMapping.getJavaType().equals(parameterObject.getClass())) {
                            paramMap.put(name, parameterObject);
                            break;
                        }
                    }
                }
            }
        }
        //备份原始参数对象
        paramMap.put(ORIGINAL_PARAMETER_OBJECT, parameterObject);
        return paramMap;
    }

    @Override
    public void setProperties(Properties properties) {
        //可以设置拦截器的属性，在这里我先忽略。
    }

    public class PageRawSqlSource implements SqlSource {

        private SqlSource sqlSource;
        private String sql;
        private List<ParameterMapping> parameterMappings;
        private Configuration configuration;
        private SqlSource original;

        public PageRawSqlSource(RawSqlSource rawSqlSource) {
            this.original = rawSqlSource;
            MetaObject metaObject = SystemMetaObject.forObject(rawSqlSource);
            StaticSqlSource staticSqlSource = (StaticSqlSource) metaObject.getValue("sqlSource");
            metaObject = SystemMetaObject.forObject(staticSqlSource);

            this.sql = (String) metaObject.getValue("sql");
            this.parameterMappings = (List<ParameterMapping>) metaObject.getValue("parameterMappings");
            this.configuration = (Configuration) metaObject.getValue("configuration");
            this.sqlSource = staticSqlSource;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            String tempSql = sql;
            tempSql = getPageSql(tempSql);
            return new BoundSql(configuration, tempSql, getPageParameterMapping(configuration, original.getBoundSql(parameterObject)), parameterObject);
        }

        public String getPageSql(String sql) {
            StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
            sqlBuilder.append(sql);
            sqlBuilder.append(" limit ?,?");
            return sqlBuilder.toString();
        }

        public List<ParameterMapping> getPageParameterMapping(Configuration configuration, BoundSql boundSql) {
            List<ParameterMapping> newParameterMappings = new ArrayList<ParameterMapping>();
            if (boundSql != null && boundSql.getParameterMappings() != null) {
                newParameterMappings.addAll(boundSql.getParameterMappings());
            }
            newParameterMappings.add(new ParameterMapping.Builder(configuration, PAGEPARAMETER_FIRST, Integer.class).build());
            newParameterMappings.add(new ParameterMapping.Builder(configuration, PAGEPARAMETER_SECOND, Integer.class).build());
            return newParameterMappings;
        }
    }
}


