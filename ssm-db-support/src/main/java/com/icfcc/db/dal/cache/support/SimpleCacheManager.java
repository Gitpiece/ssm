package com.icfcc.db.dal.cache.support;

import com.icfcc.db.dal.cache.Cache;
import com.icfcc.db.dal.cache.CacheManager;
import com.icfcc.db.dal.cache.impl.ConcurrentMapCache;
import org.springframework.beans.factory.InitializingBean;

public class SimpleCacheManager implements InitializingBean, CacheManager {

    private Cache cache;

    /**
     * Specify the Cache instances to use for this CacheManager.
     */
    public void setCache(Cache cache) {
        this.cache = cache;
    }


    public Cache getCache() {
        return cache;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (cache == null) {
            cache = new ConcurrentMapCache();
        }
    }

}
