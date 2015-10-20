package com.icfcc.db.base.page.parser;

import com.icfcc.db.base.page.PageSqlSource;
import com.icfcc.db.base.order.OrderBySqlSource;
import com.icfcc.db.base.order.OrderByStaticSqlSource;
import com.icfcc.db.base.page.Constant;
import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzh
 */
public class PageProviderSqlSource extends PageSqlSource implements OrderBySqlSource, Constant {

    private SqlSourceBuilder sqlSourceParser;
    private Class<?> providerType;
    private Method providerMethod;
    private Boolean providerTakesParameterObject;
    private SqlSource original;
    private Configuration configuration;
    private Parser parser;

    public PageProviderSqlSource(ProviderSqlSource provider, Parser parser) {
        MetaObject metaObject = SystemMetaObject.forObject(provider);
        this.sqlSourceParser = (SqlSourceBuilder) metaObject.getValue("sqlSourceParser");
        this.providerType = (Class<?>) metaObject.getValue("providerType");
        this.providerMethod = (Method) metaObject.getValue("providerMethod");
        this.providerTakesParameterObject = (Boolean) metaObject.getValue("providerTakesParameterObject");
        this.configuration = (Configuration) metaObject.getValue("sqlSourceParser.configuration");
        this.original = provider;
        this.parser = parser;
    }

    private SqlSource createSqlSource(Object parameterObject) {
        try {
            String sql;
            if (providerTakesParameterObject) {
                sql = (String) providerMethod.invoke(providerType.newInstance(), parameterObject);
            } else {
                sql = (String) providerMethod.invoke(providerType.newInstance());
            }
            Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
            StaticSqlSource sqlSource = (StaticSqlSource) sqlSourceParser.parse(sql, parameterType, new HashMap<String, Object>());
            return new OrderByStaticSqlSource(sqlSource);
        } catch (Exception e) {
            throw new BuilderException("Error invoking SqlProvider method ("
                    + providerType.getName() + "." + providerMethod.getName()
                    + ").  Cause: " + e, e);
        }
    }

    @Override
    protected BoundSql getDefaultBoundSql(Object parameterObject) {
        SqlSource sqlSource = createSqlSource(parameterObject);
        return sqlSource.getBoundSql(parameterObject);
    }

    @Override
    protected BoundSql getCountBoundSql(Object parameterObject) {
        BoundSql boundSql;
        SqlSource sqlSource = createSqlSource(parameterObject);
        boundSql = sqlSource.getBoundSql(parameterObject);
        return new BoundSql(
                configuration,
                parser.getCountSql(boundSql.getSql()),
                boundSql.getParameterMappings(),
                parameterObject);
    }

    @Override
    protected BoundSql getPageBoundSql(Object parameterObject) {
        BoundSql boundSql;
        if (parameterObject instanceof Map && ((Map) parameterObject).containsKey(PROVIDER_OBJECT)) {
            SqlSource sqlSource = createSqlSource(((Map) parameterObject).get(PROVIDER_OBJECT));
            boundSql = sqlSource.getBoundSql(((Map) parameterObject).get(PROVIDER_OBJECT));
        } else {
            SqlSource sqlSource = createSqlSource(parameterObject);
            boundSql = sqlSource.getBoundSql(parameterObject);
        }
        return new BoundSql(
                configuration,
                parser.getPageSql(boundSql.getSql()),
                parser.getPageParameterMapping(configuration, boundSql),
                parameterObject);
    }

    public SqlSource getOriginal() {
        return original;
    }
}