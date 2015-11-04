package cn.uncode.dal.mybatis.template;


import org.apache.commons.lang3.StringUtils;
import cn.uncode.dal.descriptor.Table;
import cn.uncode.dal.jdbc.template.AbstractTemplate;
import cn.uncode.dal.utils.ColumnWrapperUtils;
import cn.uncode.dal.mybatis.resolver.MybatisJavaTypeResolver;

public class SqlTemplate extends  AbstractTemplate{
    
	
	protected String buildSingleParamSql(String prefix, String column, Table model, String keyword){
        return buildSingleParamSql(prefix, column, null, model, keyword);
    }
    
    protected String buildSingleParamSql(String prefix, String column, String columnTwo, Table model, String keyword){
        StringBuilder sql = new StringBuilder();
        if(StringUtils.isNotEmpty(keyword)){
            sql.append(ColumnWrapperUtils.wrap(column)).append(" ").append(keyword);
        }
        if(StringUtils.isNotEmpty(columnTwo)){
        	sql.append(" #{").append(prefix).append(".").append(columnTwo);
        }else{
        	if(StringUtils.isNotEmpty(column)){
            	sql.append(" #{").append(prefix).append(".").append(column);
            }
        }
        String javaType = null;
        if (model.getField(column) != null) {
        	javaType = model.getField(column).getFieldSql();
		}
        if(StringUtils.isEmpty(javaType)){
            javaType = MybatisJavaTypeResolver.calculateJavaType(model.getField(column).getJdbcType());
        }
        if(StringUtils.isNotEmpty(javaType)){
            sql.append(",jdbcType=").append(javaType);
        }
        sql.append("}");
        return sql.toString();
    }
    protected String buildBetweenParamSql(String prefix, String column, Table model, String keyword){
        StringBuilder sql = new StringBuilder();
        if(StringUtils.isNotEmpty(keyword)){
            sql.append(ColumnWrapperUtils.wrap(column)).append(" ").append(keyword);
        }
        sql.append(" #{").append(prefix).append(".").append(column).append("Value");
        String javaType = model.getField(column).getFieldSql();
        if(StringUtils.isEmpty(javaType)){
            javaType = MybatisJavaTypeResolver.calculateJavaType(model.getField(column).getJdbcType());
        }
        if(StringUtils.isNotEmpty(javaType)){
            sql.append(",jdbcType=").append(javaType).append("} and #{").append(prefix).append(".").append(column)
                .append("SecondValue,jdbcType=").append(javaType).append("}");
        }else{
            sql.append("} and #{").append(prefix).append(".").append(column).append("SecondValue}");
        }
        return sql.toString();
    }
    protected String buildListParamSql(String prefix, String column, Table model, String keyword){
        StringBuilder sql = new StringBuilder();
        sql.append(ColumnWrapperUtils.wrap(column)).append(" ").append(keyword).append(" (#{").append(prefix).append(".").append(column);
        String javaType = model.getField(column).getFieldSql();
        if(StringUtils.isEmpty(javaType)){
            javaType = MybatisJavaTypeResolver.calculateJavaType(model.getField(column).getJdbcType());
        }
        if(StringUtils.isNotEmpty(javaType)){
            sql.append(",jdbcType=").append(javaType);
        }
        sql.append("})");
        return sql.toString();
    }
    
    
    
   
}
