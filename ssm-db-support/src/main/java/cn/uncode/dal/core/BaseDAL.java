package cn.uncode.dal.core;

import cn.uncode.dal.criteria.DalCriteria;
import cn.uncode.dal.descriptor.QueryResult;

import java.util.List;
import java.util.Map;

public interface BaseDAL {

    int NO_CACHE = -2;

    int PERSISTENT_CACHE = 0;

    String PAGE_INDEX_KEY = "pageIndex";
    String PAGE_SIZE_KEY = "pageSize";
    String PAGE_COUNT_KEY = "pageCount";
    String RECORD_TOTAL_KEY = "recordTotal";

    //-------------------------
    // selectByCriteria
    //-------------------------
    QueryResult selectByCriteria(List<String> fields, DalCriteria dalCriteria);

    QueryResult selectByCriteria(String[] fields, DalCriteria dalCriteria);

    QueryResult selectByCriteria(List<String> fields, DalCriteria dalCriteria, int seconds);

    QueryResult selectByCriteria(String[] fields, DalCriteria dalCriteria, int seconds);

    QueryResult selectByCriteria(DalCriteria dalCriteria);

    QueryResult selectByCriteria(DalCriteria dalCriteria, int seconds);

    QueryResult selectPageByCriteria(List<String> fields, DalCriteria dalCriteria);

    QueryResult selectPageByCriteria(String[] fields, DalCriteria dalCriteria);

    QueryResult selectPageByCriteria(List<String> fields, DalCriteria dalCriteria, int seconds);

    QueryResult selectPageByCriteria(String[] fields, DalCriteria dalCriteria, int seconds);

    QueryResult selectPageByCriteria(DalCriteria dalCriteria);

    QueryResult selectPageByCriteria(DalCriteria dalCriteria, int seconds);

    //-------------------------
    // countByCriteria
    //-------------------------
    int countByCriteria(DalCriteria dalCriteria);

    int countByCriteria(DalCriteria dalCriteria, int seconds);

    //-------------------------
    // selectByPrimaryKey
    //-------------------------
    QueryResult selectByPrimaryKey(Object obj);

//    QueryResult selectByPrimaryKey(Object obj, int seconds);

    QueryResult selectByPrimaryKey(List<String> fields, Object obj);

    QueryResult selectByPrimaryKey(String[] fields, Object obj);

    QueryResult selectByPrimaryKey(List<String> fields, Object obj, int seconds);

    QueryResult selectByPrimaryKey(String[] fields, Object obj, int seconds);

    QueryResult selectByPrimaryKey(String[] fields, String database, Object obj, int seconds);

    QueryResult selectByPrimaryKey(Class<?> clazz, Object id);

    QueryResult selectByPrimaryKey(String table, Object id);

    QueryResult selectByPrimaryKey(Class<?> clazz, Object id, int seconds);

    QueryResult selectByPrimaryKey(String table, Object id, int seconds);

    QueryResult selectByPrimaryKey(List<String> fields, Class<?> clazz, Object id);

    QueryResult selectByPrimaryKey(List<String> fields, String table, Object id);

    QueryResult selectByPrimaryKey(List<String> fields, Class<?> clazz, Object id, int seconds);

    QueryResult selectByPrimaryKey(List<String> fields, String table, Object id, int seconds);

    //-------------------------
    // insert
    //-------------------------
    Object insert(Object obj);

    Object insert(String table, Map<String, Object> obj);

    Object insert(String database, String table, Map<String, Object> obj);

    //-------------------------
    // update
    //-------------------------
    int updateByCriteria(Object obj, DalCriteria dalCriteria);

    int updateByPrimaryKey(Object obj);

    int updateByPrimaryKey(String table, Map<String, Object> obj);

    int updateByPrimaryKey(String database, String table, Map<String, Object> obj);

    //-------------------------
    // delete
    //-------------------------

    /**
     * @param obj
     * @return 删除数据行数
     */
    int deleteByPrimaryKey(Object obj);

    int deleteByPrimaryKey(String table, Map<String, Object> obj);

    int deleteByPrimaryKey(Class<?> clazz, Object id);

    int deleteByPrimaryKey(String table, Object id);

    int deleteByPrimaryKey(String database, String table, Object id);

    int deleteByCriteria(DalCriteria dalCriteria);

    //-------------------------
    // other
    //-------------------------
    void reloadTable(String tableName);

    void clearCache(String tableName);

    void reloadTable(String database, String tableName);

    void clearCache(String database, String tableName);

    public Object getTemplate();


}
