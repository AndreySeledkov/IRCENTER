package org.ircenter.server.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadLocks {

    private static final Logger LOGGER = Logger.getLogger(ReadLocks.class.getName());

    private Locks locks = new Locks(2048);
    private Map<Object, AtomicInteger> mp = Collections.synchronizedMap(new HashMap<Object, AtomicInteger>());
    // private SimpleProfiler profiler;
    private AtomicInteger waitCount = new AtomicInteger();

    public ReadLocks(String name) {
        // profiler = SimpleProfiler.get(name);
    }

    public void addLock(long id) {
        addLock(id);
    }

    public void releaseLock(long id) {
        releaseLock(id);
    }

    public void waitForRelease(long id) {
        waitForRelease(id);
    }

    public void addLock(Object key) {
        synchronized (locks.getLock(key)) {
            AtomicInteger counter = mp.get(key);
            if (counter == null) {
                mp.put(key, counter = new AtomicInteger());
            }
            counter.incrementAndGet();
        }
    }

    public void releaseLock(Object key) {
        AtomicInteger counter = null;
        boolean notify = false;
        synchronized (locks.getLock(key)) {
            counter = mp.get(key);
            if (counter == null) {
                return;
            }
            if (counter.decrementAndGet() <= 0) {
                mp.remove(key);
                notify = true;
            }
        }
        if (notify) {
            synchronized (counter) {
                counter.notifyAll();
            }
        }
    }

    public void waitForRelease(Object key) {
        AtomicInteger counter = null;
        synchronized (locks.getLock(key)) {
            counter = mp.get(key);
        }

        if (counter != null) {
            long t0 = System.currentTimeMillis();

            waitCount.incrementAndGet();
            while (counter.get() > 0) {
                synchronized (counter) {
                    try {
                        counter.wait(TimeUnit.SECONDS.toMillis(1));
                    } catch (Exception ex) {
                        LOGGER.log(Level.WARNING, "", ex);
                    }
                }
            }

            //profiler.trackDuration(System.currentTimeMillis() - t0);
        }
    }

    public int getWaitCount() {
        return waitCount.intValue();
    }

}
