package com.insigma.common.ehcache;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

import java.util.Properties;

/**
 * Ê×Ò³»º´æ¼àÌýÆ÷
 */
public class PageCacheEventListenerFactory extends CacheEventListenerFactory {
    @Override
    public CacheEventListener createCacheEventListener(Properties properties) {
       return new PageCacheEventListener();
    }
}
