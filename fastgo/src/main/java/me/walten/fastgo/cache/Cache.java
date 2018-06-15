package me.walten.fastgo.cache;
/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：缓存接口
 * -----------------------------------------------------------------
 * 2018/5/24 : Create Cache.java (Walten);
 * -----------------------------------------------------------------
 */
public interface Cache<K, V> {

    /**
     *
     * @param key key
     * @return the value or {@code null}.
     */
    V get(K key);

    /**
     *
     * @param key   key
     * @param value image
     * @return the previous value.
     */
    V put(K key, V value);

    /**
     *
     * @return the previous value or @{code null}.
     */
    V remove(K key);

    /**
     */
    void clear();

    /**
     *
     * @return max memory size.
     */
    int getMaxMemorySize();

    /**
     *
     * @return current memory size.
     */
    int getMemorySize();

}