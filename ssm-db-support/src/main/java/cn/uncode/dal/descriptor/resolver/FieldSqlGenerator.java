package cn.uncode.dal.descriptor.resolver;

import cn.uncode.dal.descriptor.Column;


public interface FieldSqlGenerator {

    String CONDITION_PREFIX = "conditions";

    String PARAM_PREFIX = "params";

    String buildSingleSql(Column field);


}
