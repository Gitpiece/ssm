package cn.uncode.dal.descriptor;

import cn.uncode.dal.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DalResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5675310807548144110L;

    private List<Map<String, Object>> resultList;

    private Map<String, Object> resultMap;

    private Map<String, Object> page;

    public void setResultList(List<Map<String, Object>> resultList) {
        this.resultList = resultList;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<String, Object> get() {
        if (resultMap != null && resultMap.size() > 0) {
            return resultMap;
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return resultMap;
    }

    public Map<String, Object> getWithAliasName(Map<String, String> aliasName) {
        Map<String, Object> result = get();
        return getWithAliasName(result, aliasName);
    }

    public Map<String, Object> getWithAliasName(List<String> hiddenFields, Map<String, String> aliasName) {
        Map<String, Object> result = get(hiddenFields);
        result = getWithAliasName(result, aliasName);
        return result;
    }


    public Map<String, Object> get(List<String> hiddenFields) {
        Map<String, Object> temp = get();
        if (temp != null) {
            for (String field : hiddenFields) {
                if (temp.containsKey(field)) {
                    temp.remove(field);
                }
            }
        }
        return temp;
    }

    public List<Map<String, Object>> getList() {
        return resultList;
    }

    public List<Map<String, Object>> getList(List<String> hiddenFields) {
        if (hiddenFields != null && resultList != null) {
            for (String field : hiddenFields) {
                for (Map<String, Object> map : resultList) {
                    if (map.containsKey(field)) {
                        map.remove(field);
                    }
                }
            }
        }
        return resultList;
    }

    public List<Map<String, Object>> getList(Map<String, String> aliasName) {
        if (aliasName != null && resultList != null) {
            resultList = getListWithAliasName(resultList, aliasName);
        }
        return resultList;
    }

    public List<Map<String, Object>> getList(List<String> hiddenFields, Map<String, String> aliasName) {
        resultList = getList(hiddenFields);
        resultList = getList(aliasName);
        return resultList;
    }

    public <T> T as(Class<T> beanClass) {
        Map<String, Object> result = get();
        if (result != null) {
            return JsonUtils.mapToObj(result, beanClass);
        }
        return null;
    }

    public <T> List<T> asList(Class<T> beanClass) {
        if (resultList != null) {
            return JsonUtils.mapListToObjList(resultList, beanClass);
        }
        return null;
    }

    public Map<String, Object> getPage() {
        return page;
    }

    public void setPage(Map<String, Object> page) {
        this.page = page;
    }

    private Map<String, Object> getWithAliasName(Map<String, Object> result, Map<String, String> aliasName) {
        if (result != null && result.size() > 0 && null != aliasName) {
            for (Entry<String, String> item : aliasName.entrySet()) {
                if (StringUtils.isNotBlank(item.getKey()) && StringUtils.isNotBlank(item.getValue())) {
                    result.put(item.getValue(), result.get(item.getKey()));
                    result.remove(item.getKey());
                }
            }
        }
        return result;
    }

    private List<Map<String, Object>> getListWithAliasName(List<Map<String, Object>> result, Map<String, String> aliasName) {
        if (result != null && result.size() > 0 && null != aliasName) {
            for (Entry<String, String> item : aliasName.entrySet()) {
                for (Map<String, Object> map : result) {
                    if (StringUtils.isNotBlank(item.getKey()) && StringUtils.isNotBlank(item.getValue())) {
                        map.put(item.getValue(), map.get(item.getKey()));
                        map.remove(item.getKey());
                    }
                }
            }
        }
        return result;
    }

}
