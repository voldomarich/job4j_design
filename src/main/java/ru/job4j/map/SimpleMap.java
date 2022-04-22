package ru.job4j.map;

import java.util.Iterator;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private Map<K, V>[] table = new Map[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count / capacity >= LOAD_FACTOR) {
            expand();
        }
        bucketIndex = indexFor(hash);
        Entry<K, V> e = table[bucketIndex];
        table[bucketIndex] = new Entry<>(hash, key, value, e);
        count++;
        modCount++;
        return table
    }

    private int hash(int hashCode) {
        return hashCode % table.length;
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        table = table[capacity] * 2;
    }

    @Override
    public V get(K key) {
        if (key.equals(table)) {
            return table[bucketIndex].get(key);
        }
    }


    @Override
    public boolean remove(K key) {
        T oldValue = get();
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return oldValue;
    }

    @Override
    public Iterator<K> iterator() {
        return iterator();
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
