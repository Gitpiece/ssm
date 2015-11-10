package com.icfcc.db.dal.mybatis;

import com.icfcc.db.dal.core.AbstractBaseDAL;
import com.icfcc.db.dal.core.BaseDAL;
import com.icfcc.db.dal.descriptor.Table;

import java.util.List;
import java.util.Map;

public class MybatisDAL extends AbstractBaseDAL implements BaseDAL {

    private CommonMapper commonMapper;

    public void setCommonMapper(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }


    @Override
    public List<Map<String, Object>> _selectByCriteria(Table table) {
        return commonMapper.selectByCriteria(table);
    }

    @Override
    public int _countByCriteria(Table table) {
        return commonMapper.countByCriteria(table);
    }

    @Override
    public Map<String, Object> selectByPrimaryKey(Table table) {
        return commonMapper.selectByPrimaryKey(table);
    }

    @Override
    public int _insert(Table table) {
        return commonMapper.insert(table);
    }

    @Override
    public int _updateByCriteria(Table table) {
        return commonMapper.updateByCriteria(table);
    }

    @Override
    public int _updateByPrimaryKey(Table table) {
        return commonMapper.updateByPrimaryKey(table);
    }

    @Override
    public int _deleteByPrimaryKey(Table table) {
        return commonMapper.deleteByPrimaryKey(table);
    }

    @Override
    public int _deleteByCriteria(Table table) {
        return commonMapper.deleteByCriteria(table);
    }


    @Override
    public boolean isNoSql() {
        return false;
    }


    @Override
    public Object getTemplate() {
        return null;
    }


}
