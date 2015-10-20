//package com.icfcc.db.base;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.mybatis.spring.SqlSessionTemplate;
//
//import javax.annotation.Resource;
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by whydk on 2015/10/19.
// */
//public abstract class AbstractBaseDao<T extends BaseDO<T>, PK extends Serializable>
//        implements BaseDao<T, PK> {
//
//    /**
//     * 作cache 结构{T类的镜像,{数据库列名,实体字段名}}
//     */
//    private static final Map<Class<?>, Map<String, String>> classFieldMap = new HashMap<Class<?>, Map<String, String>>();
//    @Resource(name = "sqlSessionTemplateASS")
//    public SqlSessionTemplate sqlSessionTemplateASS;
//    protected Log logger = LogFactory.getLog(this
//            .getClass());
//    private Class<T> entityClass;
//    //实体类主键名称
//    private String pkName;
//    //实体类ID字段名称
//    private String idName;
//    //主键的序列
//    private String seq;
//    private String tableName;
//    private Map<String, String> currentColumnFieldNames;
//
//    private SQLGenerator<T> sqlGenerator;
//
//    /**
//     *
//     */
//    @SuppressWarnings("unchecked")
//    public BaseDao() {
//        super();
//        this.entityClass = (Class<T>) GenericsUtils
//                .getSuperClassGenricType(this.getClass());
//
//        currentColumnFieldNames = classFieldMap.get(entityClass);
//        if (null == currentColumnFieldNames) {
//            currentColumnFieldNames = new LinkedHashMap<String, String>();
//            classFieldMap.put(entityClass, currentColumnFieldNames);
//        }
//
//        // 作cache
//        Field[] fields = this.entityClass.getDeclaredFields();
//
//        String fieldName = null;
//        String columnName = null;
//        for (Field field : fields) {
//            if (field.isAnnotationPresent(Ignore.class)) {
//                continue;
//            }
//            fieldName = field.getName();
//            TableColumn tableColumn = field.getAnnotation(TableColumn.class);
//            if (null != tableColumn) {
//                columnName = tableColumn.value();
//            } else {
//                columnName = null;
//            }
//            // 如果未标识特殊的列名，默认取字段名
//            columnName = (StringUtils.isEmpty(columnName) ? StringUtils
//                    .upperCase(fieldName) : columnName);
//            currentColumnFieldNames.put(columnName, fieldName);
//            if (field.isAnnotationPresent(PrimaryKey.class)) {
//                // 取得ID的列名
//                idName = fieldName;
//                pkName = columnName;
//                PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
//                seq = primaryKey.seq();
//            }
//        }
//
//        Table table = this.entityClass.getAnnotation(Table.class);
//        if (null == table) {
//            throw new RuntimeException("类-"
//                    + this.entityClass + ",未用@Table注解标识!!");
//        }
//        tableName = table.value();
//
//        sqlGenerator = new SQLGenerator<T>(currentColumnFieldNames.keySet(),
//                tableName, pkName, seq);
//    }
//
//    @Override
//    public void create(T t) {
//        sqlSessionTemplateASS.insert("create",
//                sqlGenerator.sql_create(t, currentColumnFieldNames));
//    }
//
//    @Override
//    public void createOfBatch(List<T> tList) {
//        if (null == tList || tList.isEmpty()) {
//            return;
//        }
//        int len = tList.size(), i = 0;
//        List<T> temp = new ArrayList<T>();
//        //获取列表的第一个对象的pk的value
//        Object pkVal = null;
//        for (; i < len; i++) {
//            T t = tList.get(i);
//            if (i == 0) {
//                pkVal = ReflectionUtils.invokeGetterMethod(t, idName);
//            }
//
//            temp.add(t);
//            if (i > 0 && i % AssConstant.FLUSH_CRITICAL_VAL == 0) {
//                sqlSessionTemplateASS.insert("createOfBatch", sqlGenerator
//                        .sql_createOfBatch(temp, currentColumnFieldNames, pkVal));
//                sqlSessionTemplateASS.flushStatements();
//                temp = new ArrayList<T>();
//            }
//        }
//        sqlSessionTemplateASS.insert("createOfBatch", sqlGenerator
//                .sql_createOfBatch(temp, currentColumnFieldNames, pkVal));
//    }
//
//
//    @Override
//    public void save(T t) {
//        if (StringUtils.isEmpty(seq)) {
//            this.create(t);
//        }
//        logger.info("生成序列开始:----------start----------");
//        Long nextval = sqlSessionTemplateASS.selectOne("fetchSeqNextval", "SELECT ".concat(seq).concat(" FROM DUAL"));
//        ReflectionUtils.invokeSetterMethod(t, idName, nextval);
//        logger.info("生成序列结束:---------- end ----------");
//
//        this.create(t);
//    }
//
//    @Override
//    public void saveOfBatch(List<T> tList) {
//        if (null == tList || tList.isEmpty()) {
//            return;
//        }
//        if (StringUtils.isEmpty(seq)) {
//            this.createOfBatch(tList);
//        }
//        logger.info("生成序列开始:----------start----------");
//        for (T t : tList) {
//            Long nextval = sqlSessionTemplateASS.selectOne("fetchSeqNextval", "SELECT ".concat(seq).concat(" FROM DUAL"));
//            ReflectionUtils.invokeSetterMethod(t, idName, nextval);
//        }
//        logger.info("生成序列结束:---------- end ----------");
//
//        this.createOfBatch(tList);
//    }
//
//    @Override
//    public void removeById(PK id) {
//        sqlSessionTemplateASS.delete("removeById",
//                sqlGenerator.sql_removeById(id));
//    }
//
//    @Override
//    public void removeOfBatch(List<PK> ids) {
//        if (null == ids || ids.isEmpty()) {
//            return;
//        }
//        int len = ids.size(), i = 0;
//        List<PK> temp = new ArrayList<PK>();
//        for (; i < len; i++) {
//            temp.add(ids.get(i));
//            if (i > 0 && i % AssConstant.FLUSH_CRITICAL_VAL == 0) {
//                sqlSessionTemplateASS.delete("removeOfBatch",
//                        sqlGenerator.sql_removeOfBatch(temp));
//                sqlSessionTemplateASS.flushStatements();
//                temp = new ArrayList<PK>();
//            }
//        }
//        sqlSessionTemplateASS.delete("removeOfBatch",
//                sqlGenerator.sql_removeOfBatch(temp));
//    }
//
//    @Override
//    public void removeAll() {
//        sqlSessionTemplateASS.delete("removeAll",
//                sqlGenerator.sql_removeAll());
//    }
//
//
//    @Override
//    public void modify(T t) {
//        sqlSessionTemplateASS.update("modify",
//                sqlGenerator.sql_modify(t, currentColumnFieldNames));
//
//    }
//
//    @Override
//    public void modifyOfBatch(List<T> tList) {
//        if (null == tList || tList.isEmpty()) {
//            return;
//        }
//        int len = tList.size(), i = 0;
//        for (; i < len; i++) {
//            this.modify(tList.get(i));
//            if (i > 0 && i % AssConstant.FLUSH_CRITICAL_VAL == 0) {
//                sqlSessionTemplateASS.flushStatements();
//            }
//        }
//    }
//
//    @Override
//    public T findOneById(PK id) {
//        Map<String, Object> resultMap = sqlSessionTemplateASS.selectOne(
//                "findOneById", sqlGenerator.sql_findOneById(id));
//
//        return handleResult(resultMap, this.entityClass);
//    }
//
//    @Override
//    public List<T> findAll() {
//        List<Map<String, Object>> resultMapList = sqlSessionTemplateASS
//                .selectList("findAll", sqlGenerator.sql_findAll());
//        List<T> tList = new ArrayList<T>(resultMapList.size());
//        for (Map<String, Object> resultMap : resultMapList) {
//            T t = handleResult(resultMap, this.entityClass);
//            tList.add(t);
//        }
//        return tList;
//    }
//
//    @Override
//    public Long findAllCount() {
//        Long count = sqlSessionTemplateASS
//                .selectOne("findAllCount", sqlGenerator.sql_findAllCount());
//        return count;
//    }
//
//    private T handleResult(Map<String, Object> resultMap, Class<T> tClazz) {
//        T t = null;
//        try {
//            t = tClazz.newInstance();
//        } catch (InstantiationException e) {
//            logger.error("/********************************");
//            logger.error("封装查询结果时，实例化对象(" + this.entityClass + ")时，出现异常!"
//                    + e.getMessage());
//            logger.error("/********************************");
//        } catch (IllegalAccessException e) {
//            logger.error("/********************************");
//            logger.error("封装查询结果时，实例化对象(" + this.entityClass + ")时，出现异常!"
//                    + e.getMessage());
//            logger.error("/********************************");
//        }
//        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
//            String key = entry.getKey();
//            key = currentColumnFieldNames.get(key);
//            Object val = entry.getValue();
//            ReflectionUtils.invokeSetterMethod(t, key, val);
//        }
//        return t;
//    }
//
//}