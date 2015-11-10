package com.icfcc.db.dal.utils;

import com.icfcc.db.dal.descriptor.Column;
import com.icfcc.db.dal.descriptor.resolver.JavaType;
import com.icfcc.db.dal.descriptor.resolver.JavaTypeResolver;

public class VersionWrapperUtils {

    public static String wrapSetSql(Column column) {
        JavaType javaType = JavaTypeResolver.calculateJavaType(column.getJdbcType());
        StringBuilder sql = new StringBuilder();
        if (JavaType.INTEGER == javaType) {
            sql.append(ColumnWrapperUtils.wrap(column.getFieldName()));
            sql.append("=");
            sql.append(ColumnWrapperUtils.wrap(column.getFieldName()));
            sql.append("+1");
        }
        return sql.toString();
    }

    public static String wrapWhereSql(Column column, Object value) {
        JavaType javaType = JavaTypeResolver.calculateJavaType(column.getJdbcType());
        StringBuilder sql = new StringBuilder();
        if (JavaType.INTEGER == javaType) {
            sql.append(ColumnWrapperUtils.wrap(column.getFieldName()));
            sql.append("=");
            if (value instanceof String) {
                sql.append(Integer.valueOf((String) value));
            } else {
                sql.append((Integer) value);
            }
        }
        return sql.toString();
    }

}

