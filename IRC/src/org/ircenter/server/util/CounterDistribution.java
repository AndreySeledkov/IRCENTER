package org.ircenter.server.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Seledkov Kostyantyn
 * Date: 10.05.12
 * Time: 22:01
 */

public final class CounterDistribution {

    private long valueMin = Long.MAX_VALUE;
    private long valueMax = Long.MIN_VALUE;
    private long valueCount;
    private long valueSum;
    private final long min;
    private final long max;
    private final long len;
    private final int classCount;
    private long[] array;
    private String name;
    private Lock lock = new ReentrantLock();

    public CounterDistribution(long min, long max, int classCount) {
        this(min, max, classCount, "CounterDistribution");
    }

    public CounterDistribution(long min, long max, int classCount, String name) {
        this.min = min;
        this.max = max;
        this.len = max - min;
        this.classCount = classCount;
        this.array = new long[classCount + 2];
        this.name = name;
    }

    private int getClassIndex(long value) {
        if (value < min) {
            return 0;
        }
        if (value > max) {
            return classCount + 1;
        }

        long index = value * classCount / len + 1;

//        if (index < 0)
//            index = 0;
//        if (index > classCount + 1)
//            index = classCount + 1;

        return (int) index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMax() {
        return max;
    }

    public long getMin() {
        return min;
    }

    public long getLeftBound(int classIndex) {
        return (classIndex > 0) ? (min + (classIndex - 1) * (max - min) / classCount) : Long.MIN_VALUE;
    }

    public long getRightBound(int classIndex) {
        return (classIndex < array.length - 1) ? (min + (classIndex) * (max - min) / classCount) : Long.MAX_VALUE;
    }

    public void addPoint(long value) {
        lock.lock();
        try {
            array[getClassIndex(value)] += 1;

            if (value > valueMax) {
                valueMax = value;
            }
            if (value < valueMin) {
                valueMin = value;
            }

            valueSum += value;
            valueCount += 1;
        } finally {
            lock.unlock();
        }
    }

    public void clear() {
        lock.lock();
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = 0;
            }
            valueMax = Long.MIN_VALUE;
            valueMin = Long.MAX_VALUE;
            valueSum = 0;
            valueCount = 0;
        } finally {
            lock.unlock();
        }
    }

    public int getMaxClasssIndex() {
        return array.length;
    }

    /**
     * @param classIndex [0..getMaxClasssIndex())
     * @return
     */
    public long getCount(int classIndex) {
        lock.lock();
        try {
            return array[classIndex];
        } finally {
            lock.unlock();
        }
    }

    public long getValueMax() {
        lock.lock();
        try {
            return valueMax;
        } finally {
            lock.unlock();
        }
    }

    public long getValueMin() {
        lock.lock();
        try {
            return valueMin;
        } finally {
            lock.unlock();
        }
    }

    public long getValueSum() {
        lock.lock();
        try {
            return valueSum;
        } finally {
            lock.unlock();
        }
    }

    public long getValueCount() {
        lock.lock();
        try {
            return valueCount;
        } finally {
            lock.unlock();
        }
    }

    public double getValueAverage() {
        lock.lock();
        try {
            return valueCount > 0 ? 1.0 * valueSum / valueCount : 0;
        } finally {
            lock.unlock();
        }
    }
}
