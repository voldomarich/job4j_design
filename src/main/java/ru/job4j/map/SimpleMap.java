package ru.job4j.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int bucketIndex = indexFor(hash(hashCode()));
        MapEntry<K, V> e = new MapEntry<>(key, value);
        if (table[bucketIndex] == null) {
            table[bucketIndex] = e;
            count++;
            modCount++;
        }
        return false;
    }

    private int hash(int hashCode) {
        return hashCode % table.length;
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        table = new MapEntry[capacity * 2];
    }

    @Override
    public V get(K key) {
        for (MapEntry<K, V> entry : table) {
            if (key.equals(entry)) {
                int index = Arrays.asList(table).indexOf(entry);
                MapEntry<K, V> e = table[index];
                return e.key.equals(key) ? e.value : null;
            }
            modCount++;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        for (MapEntry<K, V> entry : table) {
            if (key.equals(entry)) {
                int index = Arrays.asList(table).indexOf(entry);
                MapEntry<K, V> e = table[index];
                if (e.key.equals(key)) {
                    List<MapEntry<K, V>> list = new ArrayList<>(Arrays.asList(table));
                    list.remove(e);
                    return true;
                }
            }
            count--;
            modCount++;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<K> iterator() {
        Iterator<K> iterator;
        for (MapEntry<K, V> entry : table) {
            iterator = (Iterator<K>) entry.key;
        }
        return null;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
