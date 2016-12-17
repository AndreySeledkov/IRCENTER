package org.ircenter.serv.dataserver.images.impl.cache;

import net.sf.ehcache.constructs.blocking.CacheEntryFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:12
 */
public class SPCache implements SimpleCache {

    private SimpleCache underlyingCache;
    private CacheEntryFactory factory;
    private ConcurrentMap<Object, Entry> loading = new ConcurrentHashMap<Object, Entry>();
    private AtomicLong hits = new AtomicLong();
    private AtomicLong misses = new AtomicLong();

    public SPCache(SimpleCache underlyingCache, CacheEntryFactory factory) {
        this.underlyingCache = underlyingCache;
        this.factory = factory;
    }

    public SimpleCache getBackedCache() {
        return underlyingCache;
    }

    private Object tryLoad(Object key) {
        Entry entry = loading.get(key);
        if (entry == null) {
            Entry e1 = loading.putIfAbsent(key, entry = new Entry());
            if (e1 != null) {
                entry = e1;
            }
        }

        boolean loadInThisThread = false;
        synchronized (entry) {
            if (!entry.loaded) {
                try {
                    entry.result = factory.createEntry(key);
                } catch (Throwable ex) {
                    entry.exception = new SPCacheException("cant load Object for key=" + key, ex);
                } finally {
                    entry.loaded = true;
                    loadInThisThread = true;
                }
            }
        }
        if (loadInThisThread) {
            try {
                if (entry.result != null) {
                    underlyingCache.set(key, entry.result);
                }
            } finally {
                loading.remove(key);
            }
        }

        if (entry.exception != null) {
            throw entry.exception;
        }
        return entry.result;
    }

    @Override
    public Object get(Object key) {
        Object result = getQuiet(key);
        if (result == null) {
            result = tryLoad(key);
        }
        return result;
    }

    public Object getQuiet(Object key) {
        Object result;
        long T0 = System.nanoTime();
        try {
            result = underlyingCache.get(key);
        } finally {
        }
        if (result == null) {
            misses.incrementAndGet();
        } else {
            hits.incrementAndGet();
        }
        return result;
    }

    public void shutdown() {
        underlyingCache.shutdown();
    }

    public void set(Object key, Object value) {
        underlyingCache.set(key, value);
    }

    public void remove(Object key) {
        underlyingCache.remove(key);
    }

    private static class Entry {

        private Object result;
        private RuntimeException exception;
        private boolean loaded;
    }

    public long getHits() {
        return hits.get();
    }

    public long getMisses() {
        return misses.get();
    }

    @Override
    public long getSize() {
        return underlyingCache.getSize();
    }

    public String getEngine() {
        return underlyingCache.getClass().getSimpleName();
    }

}
