package org.ircenter.serv.dataserver.images.impl.cache;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:12
 */
public class JavaCache implements SimpleCache {

    private static final AtomicInteger counter = new AtomicInteger();
    private Cache cache;

    public JavaCache(int maxSize) {
        cache = new Cache("Cache-" + counter.incrementAndGet(), maxSize, false, true, 0, 0);
        CacheManager.getInstance().addCache(cache);
    }

    public void shutdown() {
        CacheManager.getInstance().removeCache(cache.getName());
    }

    public Object get(Object key) {
        Element element = cache.get(key);
        if (element != null) {
            return element.getObjectValue();
        }
        return null;
    }

    public void set(Object key, Object value) {
        cache.put(new Element(key, value));
    }

    public void remove(Object key) {
        cache.remove(key);
    }

    public long getSize() {
        return cache.getSize();
    }
}
