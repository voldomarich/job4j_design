package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));
        System.out.println("NEW FILE HAS BEEN ADDED IN CACHE");
    }

    public final V get(K key) {
        V result = null;
        if (cache.containsKey(key)) {
            SoftReference<V> strong = cache.get(key);
            if (strong != null) {
                result = strong.get();
            }
        } else {
            load(key);
        }
        return result;
    }

    protected abstract V load(K key);
}
