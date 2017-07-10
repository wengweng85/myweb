package com.insigma.common.ehcache;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

import org.hibernate.cache.CacheException;

/**
 * Ê×Ò³»º´æ¼àÌýÆ÷
 */
public class PageCacheEventListener implements CacheEventListener {

    @Override
    public void notifyElementPut(Ehcache ehcache, Element element) throws CacheException {
        //System.out.println(DateFormat.getDateTimeInstance().format(new Date())+" " +ehcache.getName()+ "  "+  element.getKey()+" was put");
    }

    @Override
    public void notifyElementRemoved(Ehcache ehcache, Element element) throws CacheException {
        //System.out.println(DateFormat.getDateTimeInstance().format(new Date()) +" " + ehcache.getName()+ " "+ element.getKey()+ " was removed");
    }

    @Override
    public void notifyElementUpdated(Ehcache ehcache, Element element) throws CacheException {
        //System.out.println(DateFormat.getDateTimeInstance().format(new Date())+" " +ehcache.getName()+ " "+ element.getKey()+"  was updated");
    }

    @Override
    public void notifyElementExpired(Ehcache ehcache, Element element) {
        //System.out.println(DateFormat.getDateTimeInstance().format(new Date())+" " +ehcache.getName()+ " "+element.getKey()+" was expired");
    }

    @Override
    public void notifyElementEvicted(Ehcache ehcache, Element element) {
        //System.out.println(DateFormat.getDateTimeInstance().format(new Date())+" " +ehcache.getName()+ " "+ element.getKey()+" was evicted");
    }

    @Override
    public void notifyRemoveAll(Ehcache ehcache) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
