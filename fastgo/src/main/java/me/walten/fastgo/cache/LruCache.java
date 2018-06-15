package me.walten.fastgo.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：Lru算法内存缓存
 * -----------------------------------------------------------------
 * 2018/5/24 : Create LruCache.java (Walten);
 * -----------------------------------------------------------------
 */
public class LruCache<K, V> implements Cache<K, V> {

    private static final int REMOVE_ALL = -1;

    private static final int DEFAULT_CAPACITY = 10;

    private final Map<K, V> map;

    private final int maxMemorySize;

    private int memorySize;

    @Inject
    public LruCache() {
        this(DEFAULT_CAPACITY);
    }

    public LruCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        this.map = new LruHashMap<>(capacity);
        maxMemorySize = capacity * 1024 * 1024;
    }

    @Override
    public final V get(K key) {
        Objects.requireNonNull(key, "key == null");
        synchronized (this) {
            V value = map.get(key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    @Override
    public final V put(K key, V value) {
        Objects.requireNonNull(key, "key == null");
        Objects.requireNonNull(value, "value == null");
        V previous;
        synchronized (this) {
            previous = map.put(key, value);
            memorySize += getValueSize(value);
            if (previous != null) {
                memorySize -= getValueSize(previous);
            }
            trimToSize(maxMemorySize);
        }
        return previous;
    }

    @Override
    public final V remove(K key) {
        Objects.requireNonNull(key, "key == null");
        V previous;
        synchronized (this) {
            previous = map.remove(key);
            if (previous != null) {
                memorySize -= getValueSize(previous);
            }
        }
        return previous;
    }

    @Override
    public synchronized final void clear() {
        trimToSize(REMOVE_ALL);
    }

    @Override
    public synchronized final int getMaxMemorySize() {
        return maxMemorySize;
    }

    @Override
    public synchronized final int getMemorySize() {
        return memorySize;
    }

    public synchronized final Map<K, V> snapshot() {
        return new LinkedHashMap<>(map);
    }

    protected String getClassName() {
        return LruCache.class.getName();
    }

    protected int getValueSize(V value) {
        return 1;
    }

    private void trimToSize(int maxSize) {
        while (true) {
            if (memorySize <= maxSize || map.isEmpty()) {
                break;
            }
            if (memorySize < 0 || (map.isEmpty() && memorySize != 0)) {
                throw new IllegalStateException(getClassName() + ".getValueSize() is reporting inconsistent results");
            }
            Map.Entry<K, V> toRemove = map.entrySet().iterator().next();
            map.remove(toRemove.getKey());
            memorySize -= getValueSize(toRemove.getValue());
        }
    }

    @Override
    public synchronized final String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            sb.append(entry.getKey())
                    .append('=')
                    .append(entry.getValue())
                    .append(",");
        }
        sb.append("maxMemory=")
                .append(maxMemorySize)
                .append(",")
                .append("memorySize=")
                .append(memorySize);
        return sb.toString();
    }

}