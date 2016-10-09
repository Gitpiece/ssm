package com.icfcc.db.dal.descriptor;

import java.io.Serializable;

/**
 * 数据库表列对象
 */
public class Column implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8000107635261090463L;


    /**
     * 别名
     */
    private String name;

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 类型
     */
    private int jdbcType;

    /**
     * 字段sql值
     */
    private String fieldSql;

    /**
     * 字段长度
     */
    private int length;

    /**
     * 是否必填
     */
    private boolean request;

    /**
     * 是否为主键
     */
    private boolean primaryKey;

    /**
     * 是否唯一
     */
    private boolean unique;

    /**
     * 是否可空
     */
    private boolean nullable;

    /**
     * 精度
     */
    private int scale;

    /**
     * 所属表
     */
    private String table;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName.toLowerCase();
    }

    public int getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(int jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getFieldSql() {
        return fieldSql;
    }

    public void setFieldSql(String fieldSql) {
        this.fieldSql = fieldSql;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isUnique() {
        return unique;
    }

    public boolean isNullable() {
        return nullable;
    }

    public int getScale() {
        return scale;
    }

    public String getTable() {
        return table;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", jdbcType=" + jdbcType +
                ", fieldSql='" + fieldSql + '\'' +
                ", length=" + length +
                ", request=" + request +
                ", primaryKey=" + primaryKey +
                ", unique=" + unique +
                ", nullable=" + nullable +
                ", scale=" + scale +
                ", table='" + table + '\'' +
                '}';
    }
}
