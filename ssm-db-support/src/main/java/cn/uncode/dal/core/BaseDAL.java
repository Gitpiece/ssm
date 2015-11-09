package cn.uncode.dal.core;

import cn.uncode.dal.criteria.DalCriteria;
import cn.uncode.dal.descriptor.DalResult;

import java.util.List;
import java.util.Map;

public interface BaseDAL {

    int NO_CACHE = -2;

    int PERSISTENT_CACHE = 0;

//    String PAGE_INDEX_KEY = "pageIndex";
//    String PAGE_SIZE_KEY = "pageSize";
//    String PAGE_COUNT_KEY = "pageCount";
//    String RECORD_TOTAL_KEY = "recordTotal";

    //-------------------------
    // selectByCriteria
    //-------------------------
    DalResult selectByCriteria(List<String> fields, DalCriteria dalCriteria);

    DalResult selectByCriteria(String[] fields, DalCriteria dalCriteria);

    DalResult selectByCriteria(List<String> fields, DalCriteria dalCriteria, int seconds);

    DalResult selectByCriteria(String[] fields, DalCriteria dalCriteria, int seconds);

    DalResult selectByCriteria(DalCriteria dalCriteria);

    DalResult selectByCriteria(DalCriteria dalCriteria, int seconds);

//    DalResult selectPageByCriteria(List<String> fields, DalCriteria dalCriteria);

//    DalResult selectPageByCriteria(String[] fields, DalCriteria dalCriteria);

//    DalResult selectPageByCriteria(List<String> fields, DalCriteria dalCriteria, int seconds);

//    DalResult selectPageByCriteria(String[] fields, DalCriteria dalCriteria, int seconds);

//    DalResult selectPageByCriteria(DalCriteria dalCriteria);

//    DalResult selectPageByCriteria(DalCriteria dalCriteria, int seconds);

    //-------------------------
    // countByCriteria
    //-------------------------
    int countByCriteria(DalCriteria dalCriteria);

    int countByCriteria(DalCriteria dalCriteria, int seconds);

    //-------------------------
    // selectByPrimaryKey
    //-------------------------
    DalResult selectByPrimaryKey(Object obj);

//    DalResult selectByPrimaryKey(Object obj, int seconds);

    DalResult selectByPrimaryKey(List<String> fields, Object obj);

    DalResult selectByPrimaryKey(String[] fields, Object obj);

    DalResult selectByPrimaryKey(List<String> fields, Object obj, int seconds);

    DalResult selectByPrimaryKey(String[] fields, Object obj, int seconds);

    DalResult selectByPrimaryKey(String[] fields, String database, Object obj, int seconds);

    DalResult selectByPrimaryKey(Class<?> clazz, Object id);

    DalResult selectByPrimaryKey(String table, Object id);

    DalResult selectByPrimaryKey(Class<?> clazz, Object id, int seconds);

    DalResult selectByPrimaryKey(String table, Object id, int seconds);

    DalResult selectByPrimaryKey(List<String> fields, Class<?> clazz, Object id);

    DalResult selectByPrimaryKey(List<String> fields, String table, Object id);

    DalResult selectByPrimaryKey(List<String> fields, Class<?> clazz, Object id, int seconds);

    DalResult selectByPrimaryKey(List<String> fields, String table, Object id, int seconds);

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
     * @param obj pojo对象
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

    Object getTemplate();


}
