package com.icfcc.db.dal.mybatis.resolver;

import com.icfcc.db.dal.descriptor.Column;
import com.icfcc.db.dal.descriptor.resolver.FieldSqlGenerator;

public class MybatisFieldSqlGenerator implements FieldSqlGenerator {

    @Override
    public String buildSingleSql(Column field) {
        String javaType = MybatisJavaTypeResolver.calculateJavaType(field.getJdbcType());
        return javaType;
    }


}
