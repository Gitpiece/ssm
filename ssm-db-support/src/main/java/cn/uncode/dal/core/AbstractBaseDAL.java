package cn.uncode.dal.core;

import cn.uncode.dal.cache.CacheManager;
import cn.uncode.dal.criteria.DalCriteria;
import cn.uncode.dal.criteria.Model;
import cn.uncode.dal.descriptor.Content;
import cn.uncode.dal.descriptor.DalResult;
import cn.uncode.dal.descriptor.Table;
import cn.uncode.dal.descriptor.db.ResolveDataBase;
import cn.uncode.dal.descriptor.resolver.JavaType;
import cn.uncode.dal.descriptor.resolver.JavaTypeConversion;
import cn.uncode.dal.descriptor.resolver.JavaTypeResolver;
import cn.uncode.dal.exception.StaleObjectStateException;
import cn.uncode.dal.internal.util.message.Messages;
import cn.uncode.dal.router.DefaultMasterSlaveRouter;
import cn.uncode.dal.router.MasterSlaveRouter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

public abstract class AbstractBaseDAL implements BaseDAL {

    public static final List<String> NULL_FIELDS = new ArrayList<>(0);
    private static final Log LOG = LogFactory.getLog(AbstractBaseDAL.class);

    protected CacheManager cacheManager;

    protected ResolveDataBase resolveDatabase;

    protected MasterSlaveRouter router = new DefaultMasterSlaveRouter();

    protected boolean useCache = true;

    protected String version;

//    public DalResult selectPageByCriteria(DalCriteria dalCriteria) {
//        List<String> fields = null;
//        return selectPageByCriteria(fields, dalCriteria, PERSISTENT_CACHE);
//    }

//    public DalResult selectPageByCriteria(DalCriteria dalCriteria, int seconds) {
//        List<String> fields = null;
//        return selectPageByCriteria(fields, dalCriteria, seconds);
//    }

//    public DalResult selectPageByCriteria(String[] fields, DalCriteria dalCriteria) {
//        return selectPageByCriteria(Arrays.asList(fields), dalCriteria, PERSISTENT_CACHE);
//    }

//    public DalResult selectPageByCriteria(List<String> fields, DalCriteria dalCriteria) {
//        return selectPageByCriteria(fields, dalCriteria, PERSISTENT_CACHE);
//    }

//    public DalResult selectPageByCriteria(String[] fields, DalCriteria dalCriteria, int seconds) {
//        return selectPageByCriteria(Arrays.asList(fields), dalCriteria, seconds);
//    }

//    public DalResult selectPageByCriteria(List<String> fields, DalCriteria dalCriteria, int seconds) {
//        int total = countByCriteria(dalCriteria, seconds);
//        if (total > 0) {
//            int pageCount = total / dalCriteria.getPageSize();
//            if (total % dalCriteria.getPageSize() != 0) {
//                pageCount++;
//            }
//            if (dalCriteria.getPageIndex() > pageCount) {
//                dalCriteria.setPageIndex(pageCount);
//            }
//            DalResult queryResult = selectByCriteria(fields, dalCriteria, seconds);
//            Map<String, Object> page = new HashMap<String, Object>();
//            page.put(PAGE_INDEX_KEY, dalCriteria.getPageIndex());
//            page.put(PAGE_SIZE_KEY, dalCriteria.getPageSize());
//            page.put(PAGE_COUNT_KEY, pageCount);
//            page.put(RECORD_TOTAL_KEY, total);
//            queryResult.setPage(page);
//            return queryResult;
//        }
//        return null;
//    }

    @Override
    public DalResult selectByCriteria(List<String> fields, DalCriteria dalCriteria, int seconds) {

        if (router != null) {
            router.routeToSlave();
        }

        DalResult dalResult = new DalResult();

        int hashcode = 0;
        if (fields != null) {
            for (String str : fields) {
                hashcode += str.hashCode();
            }
        }
        hashcode += dalCriteria.hashCode();
        String cacheKey = dalCriteria.getTable() + "_selectByCriteria_" + hashcode;
        if (StringUtils.isNotBlank(dalCriteria.getDatabase())) {
            cacheKey = dalCriteria.getDatabase() + "#" + cacheKey;
        }
        if (cacheManager != null && seconds != NO_CACHE && useCache) {
            List<Map<String, Object>> value = (List<Map<String, Object>>) cacheManager.getCache().getObject(cacheKey);
            if (value != null && value.size() > 0) {
                dalResult.setResultList(value);
                return dalResult;
            }
        }
        Table table = retrievalTableByQueryCriteria(dalCriteria);
        if (fields != null && fields.size() > 0) {
            LinkedHashMap<String, Object> fieldMap = new LinkedHashMap<>();
            for (String field : fields) {
                if (table.getFields().containsKey(field)) {
                    fieldMap.put(field, true);
                }
            }
            table.setParams(fieldMap);
        }
        table.setQueryCriteria(dalCriteria);

        List<Map<String, Object>> result = _selectByCriteria(table);

        //查询结果存在,才进行缓存
        if (cacheManager != null && seconds != NO_CACHE && useCache && result.size() > 0) {
            if (seconds > 0) {
                cacheManager.getCache().putObject(cacheKey, result, seconds);
            } else {
                cacheManager.getCache().putObject(cacheKey, result);
            }
        }

        if (result != null) {
            dalResult.setResultList(result);
            return dalResult;
        } else {
            return null;
        }
    }

    public abstract List<Map<String, Object>> _selectByCriteria(final Table table);

    public abstract boolean isNoSql();

    /**
     * @param dalCriteria query criteria
     * @return table
     */
    protected Table retrievalTableByQueryCriteria(DalCriteria dalCriteria) {
        if (dalCriteria == null || StringUtils.isEmpty(dalCriteria.getTable())) {
            LOG.error(Messages.getString("RuntimeError.8", "dalCriteria"));
            throw new RuntimeException(Messages.getString("RuntimeError.8", "dalCriteria"));
        }
        Table table;
        if (isNoSql()) {
            Content content = new Content();
            content.setTableName(dalCriteria.getTable());
            content.setDatabase(dalCriteria.getDatabase());
            table = new Table(content);
        } else {
            table = resolveDatabase.loadTable(dalCriteria.getDatabase(), dalCriteria.getTable(), version);
        }
        if (table == null) {
            LOG.error(Messages.getString("RuntimeError.9", dalCriteria.getTable()));
            throw new RuntimeException(Messages.getString("RuntimeError.9", dalCriteria.getTable()));
        }
        return table;
    }

    @Override
    public int countByCriteria(DalCriteria dalCriteria, int seconds) {

        if (router != null) {
            router.routeToSlave();
        }

        int hashcode = 0;
        hashcode += dalCriteria.hashCode();
        String cacheKey = dalCriteria.getTable() + "_countByCriteria_" + hashcode;
        if (StringUtils.isNotBlank(dalCriteria.getDatabase())) {
            cacheKey = dalCriteria.getDatabase() + "#" + cacheKey;
        }
        if (cacheManager != null && seconds != NO_CACHE && useCache) {
            Integer value = (Integer) cacheManager.getCache().getObject(cacheKey);
            if (value != null && value > 0) {
                return value;
            }
        }
        Table table = retrievalTableByQueryCriteria(dalCriteria);
        table.setQueryCriteria(dalCriteria);
        int result = _countByCriteria(table);
        if (cacheManager != null && seconds != NO_CACHE && useCache && result > 0) {
            if (seconds > 0) {
                cacheManager.getCache().putObject(cacheKey, result, seconds);
            } else {
                cacheManager.getCache().putObject(cacheKey, result);
            }
        }
        return result;
    }

    public abstract int _countByCriteria(final Table table);


    @SuppressWarnings("unchecked")
    public DalResult selectByPrimaryKey(Object obj) {
        return selectByPrimaryKey(NULL_FIELDS, obj, PERSISTENT_CACHE);
    }

//	public DalResult selectByPrimaryKey(Object obj, int seconds) {
//    	List<String> fields = null;
//    	return selectByPrimaryKey(fields, obj, seconds);
//	}

    @Override
    public DalResult selectByPrimaryKey(String[] fields, Object obj) {
        return selectByPrimaryKey(Arrays.asList(fields), obj, PERSISTENT_CACHE);
    }

    @Override
    public DalResult selectByPrimaryKey(String[] fields, Object obj, int seconds) {
        return selectByPrimaryKey(Arrays.asList(fields), obj, seconds);
    }

    public DalResult selectByPrimaryKey(List<String> fields, Object obj) {
        return selectByPrimaryKey(fields, obj, PERSISTENT_CACHE);
    }

    public DalResult selectByPrimaryKey(List<String> fields, Object obj, int seconds) {
        return selectByPrimaryKey(fields, new Model(obj), seconds);
    }

    public DalResult selectByPrimaryKey(Class<?> clazz, Object id) {
        return selectByPrimaryKey(null, clazz, id, PERSISTENT_CACHE);
    }

    public DalResult selectByPrimaryKey(Class<?> clazz, Object id, int seconds) {
        return selectByPrimaryKey(null, clazz, id, seconds);
    }

    public DalResult selectByPrimaryKey(List<String> fields, Class<?> clazz, Object id) {
        return selectByPrimaryKey(fields, clazz, id, PERSISTENT_CACHE);
    }

    public DalResult selectByPrimaryKey(List<String> fields, Class<?> clazz, Object id, int seconds) {
        Model model = new Model(clazz);
        model.setSinglePrimaryKey(id);
        return selectByPrimaryKey(fields, model, seconds);
    }

    @Override
    @SuppressWarnings("unchecked")
    public DalResult selectByPrimaryKey(String table, Object id) {
        Model model = new Model(table);
        model.setSinglePrimaryKey(id);
        return selectByPrimaryKey(NULL_FIELDS, model, PERSISTENT_CACHE);
    }


    @Override
    @SuppressWarnings("unchecked")
    public DalResult selectByPrimaryKey(String table, Object id, int seconds) {
        Model model = new Model(table);
        model.setSinglePrimaryKey(id);
        return selectByPrimaryKey(NULL_FIELDS, model, seconds);
    }


    @Override
    public DalResult selectByPrimaryKey(List<String> fields, String table, Object id) {
        Model model = new Model(table);
        model.setSinglePrimaryKey(id);
        return selectByPrimaryKey(fields, model, PERSISTENT_CACHE);
    }


    @Override
    public DalResult selectByPrimaryKey(List<String> fields, String table, Object id, int seconds) {
        Model model = new Model(table);
        model.setSinglePrimaryKey(id);
        return selectByPrimaryKey(fields, model, seconds);
    }

    @Override
    public DalResult selectByPrimaryKey(String[] fields, String database, Object obj, int seconds) {
        Model model = new Model(obj);
        model.setDatabase(database);
        return selectByPrimaryKey(fields, model, seconds);
    }

    private DalResult selectByPrimaryKey(List<String> fields, Model model, int seconds) {
        if (model == null) {
            return null;
        }
        if (router != null) {
            router.routeToSlave();
        }

        DalResult dalResult = new DalResult();
        int hashcode = 0;
        if (fields != null) {
            for (String str : fields) {
                hashcode += str.hashCode();
            }
        }
        hashcode += model.hashCode();
        String cacheKey = model.getTableName() + "_selectByPrimaryKey_" + hashcode;
        if (StringUtils.isNotBlank(model.getDatabase())) {
            cacheKey = model.getDatabase() + "#" + cacheKey;
        }
        if (cacheManager != null && seconds != NO_CACHE && useCache) {
            Map<String, Object> value = (Map<String, Object>) cacheManager.getCache().getObject(cacheKey);
            if (value != null && value.size() > 0) {
                dalResult.setResultMap(value);
                return dalResult;
            }
        }
        Table table = retrievalTableByModel(model);
        if (fields != null && fields.size() > 0) {
            LinkedHashMap<String, Object> fieldMap = new LinkedHashMap<>();
            for (String field : fields) {
                if (table.getFields().containsKey(field)) {
                    fieldMap.put(field, true);
                }
            }
            table.setParams(fieldMap);
        }
        if (isNoSql()) {
            if (null != model.getSinglePrimaryKey()) {
                LinkedHashMap<String, Object> condistions = new LinkedHashMap<>();
                condistions.put("_id", model.getSinglePrimaryKey());
                table.setConditions(condistions);
            }
        } else {
            List<String> names = table.getPrimaryKey().getFields();
            if (null != model.getSinglePrimaryKey()) {
                if (null != names && names.size() > 0) {
                    LinkedHashMap<String, Object> condistions = new LinkedHashMap<>();
                    condistions.put(names.get(0), model.getSinglePrimaryKey());
                    table.setConditions(condistions);
                }
            } else {
                table.setConditions(model.getContent());
            }
        }
        Map<String, Object> result = selectByPrimaryKey(table);

        if (cacheManager != null && seconds != NO_CACHE && useCache && result != null && result.size() > 0) {
            if (seconds > 0) {
                cacheManager.getCache().putObject(cacheKey, result, seconds);
            } else {
                cacheManager.getCache().putObject(cacheKey, result);
            }
        }
        if (result != null) {
            dalResult.setResultMap(result);
            return dalResult;
        } else {
            return null;
        }
    }

    public abstract Map<String, Object> selectByPrimaryKey(final Table table);

    /**
     * @param model instance
     * @return table
     */
    protected Table retrievalTableByModel(Model model) {
        if (model == null || StringUtils.isEmpty(model.getTableName())) {
            LOG.error(Messages.getString("RuntimeError.8", "model"));
            throw new RuntimeException(Messages.getString("RuntimeError.8", "model"));
        }
        Table table;
        if (isNoSql()) {
            Content content = new Content();
            content.setTableName(model.getTableName());
            content.setDatabase(model.getDatabase());
            table = new Table(content);
        } else {
            table = resolveDatabase.loadTable(model.getDatabase(), model.getTableName(), version);
        }
        if (table == null) {
            LOG.error(Messages.getString("RuntimeError.9", model.getTableName()));
            throw new RuntimeException(Messages.getString("RuntimeError.9", model.getTableName()));
        }
        return table;
    }

    @Override
    public Object insert(Object obj) {
        return insert(new Model(obj));
    }

    public Object insert(String table, Map<String, Object> obj) {
        Model model = new Model(table);
        model.addContent(obj);
        return insert(model);
    }

    @Override
    public Object insert(String database, String table, Map<String, Object> obj) {
        Model model = new Model(database, table);
        model.addContent(obj);
        return insert(model);
    }

    private Object insert(Model model) {
        if (router != null) {
            router.routeToMaster();
        }
        Table table = retrievalTableByModel(model);
        if (model != null && model.getContent() != null && model.getContent().size() > 0) {
            table.setParams(model.getContent());
        } else {
            LOG.error(Messages.getString("RuntimeError.8", "model.params"));
            throw new RuntimeException(Messages.getString("RuntimeError.8", "model.params"));
        }

        int result = _insert(table);
        Object idObj = null;
        if (result > 0) {
            idObj = table.getParams().get("id");
            if (null == idObj) {
                idObj = result;
            }
        }

        if (cacheManager != null && useCache) {
            String cacheKey = model.getTableName();
            if (StringUtils.isNotEmpty(model.getDatabase())) {
                cacheKey = model.getDatabase() + "#" + cacheKey;
            }
            cacheManager.getCache().clear(cacheKey);
        }

        return idObj;
    }


    /**
     * insert option
     *
     * @param table table instance
     * @return result
     */
    public abstract int _insert(Table table);

    @Override
    public int updateByCriteria(Object obj, DalCriteria dalCriteria) {
        return updateByCriteria(new Model(obj), dalCriteria);
    }

    private int updateByCriteria(Model model, DalCriteria dalCriteria) {
        if (router != null) {
            router.routeToMaster();
        }
        Table table = retrievalTableByQueryCriteria(dalCriteria);
        if (model != null && model.getContent() != null && model.getContent().size() > 0) {
            table.setParams(model.getContent());
        } else {
            LOG.error(Messages.getString("RuntimeError.8", "model.params"));
            throw new RuntimeException(Messages.getString("RuntimeError.8", "model.params"));
        }

        table.setQueryCriteria(dalCriteria);

        int result = _updateByCriteria(table);
        if (cacheManager != null && useCache) {
            String cacheKey = model.getTableName();
            if (StringUtils.isNotEmpty(model.getDatabase())) {
                cacheKey = model.getDatabase() + "#" + cacheKey;
            }
            cacheManager.getCache().clear(cacheKey);
        }

        return result;
    }

    public abstract int _updateByCriteria(Table table);

    @Override
    public int updateByPrimaryKey(Object obj) {
        return updateByPrimaryKey(new Model(obj));
    }

    public int updateByPrimaryKey(String table, Map<String, Object> obj) {
        Model model = new Model(table);
        model.addContent(obj);
        return updateByPrimaryKey(model);
    }

    @Override
    public int updateByPrimaryKey(String database, String table,
                                  Map<String, Object> obj) {
        Model model = new Model(database, table);
        model.addContent(obj);
        return updateByPrimaryKey(model);
    }

    private int updateByPrimaryKey(Model model) {
        if (router != null) {
            router.routeToMaster();
        }
        Table table = retrievalTableByModel(model);
        if (model != null && model.getContent() != null && model.getContent().size() > 0) {
            if (isNoSql()) {
                if (null != model.getSinglePrimaryKey()) {
                    model.getContent().put("_id", model.getSinglePrimaryKey());
                }
            } else {
                List<String> names = table.getPrimaryKey().getFields();
                if (null != model.getSinglePrimaryKey() && names.size() == 1) {
                    model.getContent().put(names.get(0), model.getSinglePrimaryKey());
                }
            }
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            LinkedHashMap<String, Object> conditions = new LinkedHashMap<>();
            for(Map.Entry<String,Object> entry: model.getContent().entrySet()){
                String key = entry.getKey();
                Object value = entry.getValue();
                if (null != value) {
                    if (isNoSql()) {
                        params.put(key, value);
                    } else {
                        JavaType javaType = JavaTypeResolver.calculateJavaType(table.getField(key).getJdbcType());
                        if (table.getPrimaryKey().getFields().contains(key)) {
                            conditions.put(key, JavaTypeConversion.convert(javaType, value));
                        } else {
                            params.put(key, JavaTypeConversion.convert(javaType, value));
                        }
                    }
                } else {
                    params.put(key, null);
                }
            }
            if (table.hasVersion()) {
                Object value = model.getVersion();
                if (null == value) {
                    throw new StaleObjectStateException("Version is request.");
                }
                conditions.put(version, value);
            }
            table.setParams(params);
            table.setConditions(conditions);
        } else {
            LOG.error(Messages.getString("RuntimeError.8", "model.params"));
            throw new RuntimeException(Messages.getString("RuntimeError.8", "model.params"));
        }
        int result = _updateByPrimaryKey(table);

        if (cacheManager != null && useCache) {
            String cacheKey = model.getTableName();
            if (StringUtils.isNotEmpty(model.getDatabase())) {
                cacheKey = model.getDatabase() + "#" + cacheKey;
            }
            cacheManager.getCache().clear(cacheKey);
        }

        return result;
    }


    public abstract int _updateByPrimaryKey(Table table);


    public int deleteByPrimaryKey(Class<?> clazz, Object id) {
        Model model = new Model(clazz);
        model.setSinglePrimaryKey(id);
        return deleteByPrimaryKey(model);
    }

    public int deleteByPrimaryKey(String table, Object id) {
        Model model = new Model(table);
        model.setSinglePrimaryKey(id);
        return deleteByPrimaryKey(model);
    }

    @Override
    public int deleteByPrimaryKey(String database, String table, Object id) {
        Model model = new Model(database, table);
        model.setSinglePrimaryKey(id);
        return deleteByPrimaryKey(model);
    }

    public int deleteByPrimaryKey(Object obj) {
        return deleteByPrimaryKey(new Model(obj));
    }

    public int deleteByPrimaryKey(String table, Map<String, Object> obj) {
        Model model = new Model(table);
        model.addContent(obj);
        return deleteByPrimaryKey(model);
    }

    private int deleteByPrimaryKey(Model model) {
        if (router != null) {
            router.routeToMaster();
        }
        Table table = retrievalTableByModel(model);
        if (model != null) {
            if (isNoSql()) {
                if (null != model.getSinglePrimaryKey()) {
                    LinkedHashMap<String, Object> condistions = new LinkedHashMap<>();
                    condistions.put("_id", model.getSinglePrimaryKey());
                    table.setConditions(condistions);
                }
            } else {
                List<String> names = table.getPrimaryKey().getFields();
                if (null != model.getSinglePrimaryKey() && names.size() == 1) {
                    LinkedHashMap<String, Object> condistions = new LinkedHashMap<>();
                    condistions.put(names.get(0), model.getSinglePrimaryKey());
                    table.setConditions(condistions);
                } else {
                    table.setConditions(model.getContent());
                }
            }
        } else {
            LOG.error(Messages.getString("RuntimeError.8", "model.conditions"));
            throw new RuntimeException(Messages.getString("RuntimeError.8", "model.conditions"));
        }

        int result = _deleteByPrimaryKey(table);
        if (cacheManager != null && useCache) {
            String cacheKey = model.getTableName();
            if (StringUtils.isNotEmpty(model.getDatabase())) {
                cacheKey = model.getDatabase() + "#" + cacheKey;
            }
            cacheManager.getCache().clear(cacheKey);
        }

        return result;
    }

    public abstract int _deleteByPrimaryKey(Table table);


    @Override
    public int deleteByCriteria(DalCriteria dalCriteria) {
        if (router != null) {
            router.routeToMaster();
        }
        Table table = retrievalTableByQueryCriteria(dalCriteria);
        table.setQueryCriteria(dalCriteria);
        int result = _deleteByCriteria(table);
        if (cacheManager != null && useCache) {
            String cacheKey = dalCriteria.getTable();
            if (StringUtils.isNotEmpty(dalCriteria.getDatabase())) {
                cacheKey = dalCriteria.getDatabase() + "#" + cacheKey;
            }
            cacheManager.getCache().clear(cacheKey);
        }
        return result;
    }

    public abstract int _deleteByCriteria(Table table);


    @Override
    @SuppressWarnings("unchecked")
    public DalResult selectByCriteria(DalCriteria dalCriteria, int seconds) {
        return selectByCriteria(NULL_FIELDS, dalCriteria, seconds);
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void setResolveDatabase(ResolveDataBase resolveDatabase) {
        this.resolveDatabase = resolveDatabase;
    }

    public void setRouter(MasterSlaveRouter router) {
        this.router = router;
    }

    public void reloadTable(String tableName) {
        reloadTable(null, tableName);
    }

    public void clearCache(String tableName) {
        clearCache(null, tableName);
    }

    public void reloadTable(String database, String tableName) {
        resolveDatabase.reloadTable(database, tableName);
    }

    public void clearCache(String database, String tableName) {
        String cacheKey = tableName;
        if (StringUtils.isNotEmpty(database)) {
            cacheKey = database + "#" + cacheKey;
        }
        this.cacheManager.getCache().clear(cacheKey);
    }

    @Override
    public DalResult selectByCriteria(String[] fields, DalCriteria dalCriteria) {
        return selectByCriteria(Arrays.asList(fields), dalCriteria, PERSISTENT_CACHE);
    }


    @Override
    public DalResult selectByCriteria(String[] fields, DalCriteria dalCriteria, int seconds) {
        return selectByCriteria(Arrays.asList(fields), dalCriteria, seconds);
    }


    @Override
    public DalResult selectByCriteria(List<String> fields, DalCriteria dalCriteria) {
        return selectByCriteria(fields, dalCriteria, PERSISTENT_CACHE);
    }

    @Override
    public DalResult selectByCriteria(DalCriteria dalCriteria) {
        return selectByCriteria(dalCriteria, PERSISTENT_CACHE);
    }

    @Override
    public int countByCriteria(DalCriteria dalCriteria) {
        return countByCriteria(dalCriteria, PERSISTENT_CACHE);
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    public String isVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}
