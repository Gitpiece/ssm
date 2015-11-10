package com.icfcc.db.dal.spring.jdbc.template;

import com.icfcc.db.dal.descriptor.Table;
import com.icfcc.db.dal.jdbc.template.AbstractTemplate;
import com.icfcc.db.dal.utils.ColumnWrapperUtils;
import org.apache.commons.lang3.StringUtils;

public class SqlTemplate extends AbstractTemplate {


    protected String buildSingleParamSql(String prefix, String column, String columnTwo, Table model, String keyword) {
        StringBuffer sql = new StringBuffer();
        if (StringUtils.isNotEmpty(keyword)) {
            sql.append(ColumnWrapperUtils.wrap(column)).append(" ").append(keyword);
        }
        sql.append(" ? ");
        return sql.toString();
    }

    protected String buildSingleParamSql(String prefix, String column, Table model, String keyword) {
        return buildSingleParamSql(prefix, column, null, model, keyword);
    }

    protected String buildBetweenParamSql(String prefix, String column, Table model, String keyword) {
        StringBuffer sql = new StringBuffer();
        if (StringUtils.isNotEmpty(keyword)) {
            sql.append(ColumnWrapperUtils.wrap(column)).append(" ").append(keyword);
        }
        sql.append(" ? and ? ");
        return sql.toString();
    }

    protected String buildListParamSql(String prefix, String column, Table model, String keyword) {
        StringBuffer sql = new StringBuffer();
        sql.append(ColumnWrapperUtils.wrap(column)).append(" ").append(keyword).append(" (?) ");
        return sql.toString();
    }


}
