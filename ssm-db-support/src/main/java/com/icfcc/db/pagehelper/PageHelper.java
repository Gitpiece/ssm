package com.icfcc.db.pagehelper;

import com.icfcc.db.orderhelper.OrderByHelper;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Mybatis - 通用分页拦截器
 *
 * @author liuzh/abel533/isea533
 * @version 3.3.0
 *          项目地址 : http://git.oschina.net/free/Mybatis_PageHelper
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class PageHelper implements Interceptor {
    //sql工具类
    private SqlUtil sqlUtil;
    //属性参数信息
    private Properties properties;
    //自动获取dialect
    private Boolean autoDialect;

    /**
     * 开始分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     */
    public static Page startPage(int pageNum, int pageSize) {
        return startPage(pageNum, pageSize, true);
    }

    /**
     * 开始分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @param count    是否进行count查询
     */
    public static Page startPage(int pageNum, int pageSize, boolean count) {
        return startPage(pageNum, pageSize, count, null);
    }

    /**
     * 开始分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @param orderBy  排序
     */
    public static Page startPage(int pageNum, int pageSize, String orderBy) {
        orderBy(orderBy);
        return startPage(pageNum, pageSize);
    }

    /**
     * 开始分页
     *
     * @param pageNum    页码
     * @param pageSize   每页显示数量
     * @param count      是否进行count查询
     * @param reasonable 分页合理化,null时用默认配置
     */
    public static Page startPage(int pageNum, int pageSize, boolean count, Boolean reasonable) {
        return startPage(pageNum, pageSize, count, reasonable, null);
    }

    /**
     * 开始分页
     *
     * @param pageNum      页码
     * @param pageSize     每页显示数量
     * @param count        是否进行count查询
     * @param reasonable   分页合理化,null时用默认配置
     * @param pageSizeZero true且pageSize=0时返回全部结果，false时分页,null时用默认配置
     */
    public static Page startPage(int pageNum, int pageSize, boolean count, Boolean reasonable, Boolean pageSizeZero) {
        Page page = new Page(pageNum, pageSize, count);
        page.setReasonable(reasonable);
        page.setPageSizeZero(pageSizeZero);
        SqlUtil.setLocalPage(page);
        return page;
    }

    /**
     * 开始分页
     *
     * @param params
     */
    public static Page startPage(Object params) {
        Page page = SqlUtil.getPageFromObject(params);
        SqlUtil.setLocalPage(page);
        return page;
    }

    /**
     * 排序
     *
     * @param orderBy
     */
    public static void orderBy(String orderBy) {
        OrderByHelper.orderBy(orderBy);
    }

    /**
     * Mybatis拦截器方法
     *
     * @param invocation 拦截器入参
     * @return 返回执行结果
     * @throws Throwable 抛出异常
     */
    public Object intercept(Invocation invocation) throws Throwable {
        if (autoDialect) {
            initSqlUtil(invocation);
        }
        return sqlUtil.processPage(invocation);
    }

    /**
     * 初始化sqlUtil
     *
     * @param invocation
     */
    public synchronized void initSqlUtil(Invocation invocation) {
        if (sqlUtil == null) {
            String url = null;
            try {
                MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
                MetaObject msObject = SystemMetaObject.forObject(ms);
                DataSource dataSource = (DataSource) msObject.getValue("configuration.environment.dataSource");
                url = dataSource.getConnection().getMetaData().getURL();
            } catch (SQLException e) {
                throw new RuntimeException("分页插件初始化异常:" + e.getMessage());
            }
            if (url == null || url.length() == 0) {
                throw new RuntimeException("无法自动获取jdbcUrl，请在分页插件中配置dialect参数!");
            }
            String dialect = Dialect.fromJdbcUrl(url);
            if (dialect == null) {
                throw new RuntimeException("无法自动获取数据库类型，请通过dialect参数指定!");
            }
            sqlUtil = new SqlUtil(dialect);
            sqlUtil.setProperties(properties);
            properties = null;
            autoDialect = false;
        }
    }

    /**
     * 只拦截Executor
     *
     * @param target
     * @return
     */
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    /**
     * 设置属性值
     *
     * @param p 属性值
     */
    public void setProperties(Properties p) {
        //MyBatis3.2.0版本校验
        try {
            Class.forName("org.apache.ibatis.scripting.xmltags.SqlNode");//SqlNode是3.2.0之后新增的类
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("您使用的MyBatis版本太低，MyBatis分页插件PageHelper支持MyBatis3.2.0及以上版本!");
        }
        //数据库方言
        String dialect = p.getProperty("dialect");
        if (dialect == null || dialect.length() == 0) {
            autoDialect = true;
            this.properties = p;
        } else {
            autoDialect = false;
            sqlUtil = new SqlUtil(dialect);
            sqlUtil.setProperties(p);
        }
    }
}
