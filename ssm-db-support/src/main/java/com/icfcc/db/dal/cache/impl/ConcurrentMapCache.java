package com.icfcc.db.dal.cache.impl;

import com.icfcc.db.dal.cache.Cache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapCache implements Cache {

    private static final Log LOG = LogFactory.getLog(ConcurrentMapCache.class);

    private static final ConcurrentMap<Object, Object> cache = new ConcurrentHashMap<>();

    public int getSize() {
        return cache.size();
    }

    public void putObject(Object key, Object value) {
        cache.put(key, value);
        LOG.debug("Storing " + key + " to cache.");
    }

    public Object getObject(Object key) {
        Object value = cache.get(key);
        if (LOG.isDebugEnabled()) {
            if (value == null) {
                LOG.debug(key + " cache not exists.");
            } else {
                LOG.debug("Reading " + key + " from cache.");
            }
        }
        return value;
    }

    public Object removeObject(Object key) {
        LOG.debug("Removing " + key + " from cache.");
        return cache.remove(key);
    }

    public void clear(String id) {
//        Iterator<Object> iter = cache.keySet().iterator();
        for (Object keyObj : cache.keySet()) {
//        while(iter.hasNext()){
            String key = String.valueOf(keyObj);
            if (key.contains(id)) {
                LOG.debug("Clearing " + key + " from cache.");
                cache.remove(key);
            }
        }
        LOG.debug("Clearing *" + id + "* from cache.");
    }

    @Override
    public void putObject(Object key, Object value, int seconds) {
        putObject(key, value);
        LOG.debug("Storing " + key + " to cache[seconds:" + seconds + "].");
    }

}
