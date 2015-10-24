package com.icfcc.db.pagehelper.parser;


import com.icfcc.db.pagehelper.Page;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

import java.util.Map;

/**
 * @author liuzh
 */
public class Db2Parser extends AbstractParser {
    @Override
    public String getPageSql(String sql) {
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 120);
        sqlBuilder.append("select * from (select tmp_page.*,rownumber() over() as row_id from ( ");
        sqlBuilder.append(sql);
        sqlBuilder.append(" ) as tmp_page) where row_id between  ? and ?");
        return sqlBuilder.toString();
    }

    @Override
    public Map setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page page) {
        Map paramMap = super.setPageParameter(ms, parameterObject, boundSql, page);
        paramMap.put(PAGEPARAMETER_FIRST, page.getStartRow() + 1);
        paramMap.put(PAGEPARAMETER_SECOND, page.getEndRow());
        return paramMap;
    }
}