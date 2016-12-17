package org.ircenter.server.util;

import net.sf.ehcache.concurrent.ConcurrencyUtil;

/**
 * User: Seledkov Kostyantyn
 * Date: 28.03.12
 * Time: 1:25
 */
public class Locks {
    private Object[] locks;

    public Locks(int size) {
        if ((size & (size - 1)) != 0) {
            throw new IllegalArgumentException("Lock number must be a power of two: " + size);
        }

        locks = new Object[size];
        for (int i = 0; i < size; ++i) {
            locks[i] = new Object();
        }
    }

    public Object getLock(Object object) {
        int index = ConcurrencyUtil.selectLock(object, locks.length);
        return locks[index];
    }
}
