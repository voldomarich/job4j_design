package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int bucketIndex = indexFor(hash(key.hashCode()));
        MapEntry<K, V> e = new MapEntry<>(key, value);
        if (table[bucketIndex] == null) {
            table[bucketIndex] = e;
            count++;
            modCount++;
        }
        return table[bucketIndex] == e && e.key != null;
    }

    private int hash(int hashCode) {
        return hashCode % table.length;
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            int index = indexFor(hash(entry.key.hashCode()));
            if (entry.key != null) {
                newTable[index] = entry;
                table = newTable;
            }
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> e = table[index];
        if (e.key != null) {
            return e.key.equals(key) ? e.value : null;
        }
        return e.value;
    }

    public int size() {
        return table.length;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> e = table[index];
        if (e.key.equals(key)) {
            table[index] = null;
        }
        count--;
        modCount++;
        return table[index] == null;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int point = 0;

            @Override
            public boolean hasNext() {
                for (int i = point; i < table.length; i++) {
                    if (table[i] != null) {
                        point = i;
                        break;
                    }
                }
                return point < table.length && table[point] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
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
