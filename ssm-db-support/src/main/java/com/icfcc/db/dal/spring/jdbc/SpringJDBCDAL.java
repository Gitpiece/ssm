package com.icfcc.db.dal.spring.jdbc;

import java.util.List;
import java.util.Map;

import com.icfcc.db.dal.core.AbstractBaseDAL;
import com.icfcc.db.dal.core.BaseDAL;
import com.icfcc.db.dal.descriptor.Table;

public class SpringJDBCDAL extends AbstractBaseDAL implements BaseDAL {

    private CommonJdbcSupport commonJdbcSupport;

    public void setCommonJdbcSupport(CommonJdbcSupport commonJdbcSupport) {
        this.commonJdbcSupport = commonJdbcSupport;
    }

    @Override
    public List<Map<String, Object>> _selectByCriteria(Table table) {
        return commonJdbcSupport.selectByCriteria(table);
    }

    @Override
    public int _countByCriteria(Table table) {
        return commonJdbcSupport.countByCriteria(table);
    }

    @Override
    public Map<String, Object> selectByPrimaryKey(Table table) {
        throw new IllegalAccessError("not support.");
    }

//	@Override
//	public Map<String, Object> selectByPrimaryKey(Table table) {
//		return null;
//	}

    //	@Override
    public Map<String, Object> _selectByPrimaryKey(Table table) {
        return commonJdbcSupport.selectByPrimaryKey(table);
    }

    @Override
    public int _insert(Table table) {
        return commonJdbcSupport.insert(table);
    }

    @Override
    public int _updateByCriteria(Table table) {
        return commonJdbcSupport.updateByCriteria(table);
    }

    @Override
    public int _updateByPrimaryKey(Table table) {
        return commonJdbcSupport.updateByPrimaryKey(table);
    }

    @Override
    public int _deleteByPrimaryKey(Table table) {
        return commonJdbcSupport.deleteByPrimaryKey(table);
    }

    @Override
    public int _deleteByCriteria(Table table) {
        return commonJdbcSupport.deleteByCriteria(table);
    }


    @Override
    public boolean isNoSql() {
        return false;
    }

    @Override
    public Object getTemplate() {
        return commonJdbcSupport.getJdbcTemplate();
    }

}
