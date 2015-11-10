package com.icfcc.db.dal.descriptor.resolver;

import com.icfcc.db.dal.descriptor.Column;


public interface FieldSqlGenerator {

    String CONDITION_PREFIX = "conditions";

    String PARAM_PREFIX = "params";

    String buildSingleSql(Column field);


}
