package com.icfcc.db.dal.criteria;

import com.icfcc.db.dal.descriptor.TableInfoResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Layer标准
 */
public class DalCriteria {

    private String database;

    private String table;

    private String orderByClause;

    private String groupBy;

    private boolean distinct;

    private List<Criteria> oredCriteria;

    private boolean selectOne;

//    private int pageIndex = 1;

//    private int pageSize = 10;

    private int recordIndex = 0;

    private Object version;

    public DalCriteria() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

//    public int getPageIndex() {
//        return pageIndex;
//    }

//    public void setPageIndex(int pageIndex) {
//        this.pageIndex = pageIndex;
//    }

//    public int getPageSize() {
//        return pageSize;
//    }

//    public void setPageSize(int pageSize) {
//        this.pageSize = pageSize;
//    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table.toLowerCase().trim();
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public int getRecordIndex() {
        return recordIndex;
    }

    public void setRecordIndex(int recordIndex) {
        this.recordIndex = recordIndex;
    }

    public void setTable(Class<?> clazz) {
        this.table = TableInfoResolver.resolverTable(clazz);
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.isEmpty()) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        return new Criteria();
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setSelectOne(boolean selectOne) {
        this.selectOne = selectOne;
    }

    public boolean getSelectOne() {
        return this.selectOne;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderByClause == null) ? 0 : orderByClause.hashCode());
        result = prime * result + ((database == null) ? 0 : database.hashCode());
        result = prime * result + ((table == null) ? 0 : table.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        result = prime * result + (distinct ? 1231 : 1237);
        result = prime * result + (selectOne ? 1231 : 1237);
        result = prime * result + (selectOne ? 1231 : 1237);
//        result = prime * result + pageIndex + pageSize;
        if (oredCriteria != null) {
            for (Criteria criteria : oredCriteria) {
                for (Criterion cter : criteria.getAllCriteria()) {
                    result = prime * result + ((cter == null) ? 0 : cter.hashCode());
                }
            }
        }
        return result;
    }




}