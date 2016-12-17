package org.ircenter.serv.dataserver.images.impl.cache;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:12
 */

public interface SimpleCache {
    public void shutdown();

    public Object get(Object key);

    public void set(Object key, Object value);

    public void remove(Object key);

    public long getSize();
}
