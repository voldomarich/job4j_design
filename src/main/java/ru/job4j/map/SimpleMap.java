package ru.job4j.map;

import net.sf.saxon.ma.map.MapFunctionSet;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V>  {

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
        Node<E> e;
        if (hash == hash && ((k = key) == key || key.equals(k))) {
            e.value = value;
        }

    }

}


    private int hash(int hashCode) {
        return hashCode % table.length;
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {


    }

    @Override
    public V get(K key) {
    if (key.equals(table)) {
        return table[key];
        return null;
    }

    @Override
    public boolean remove(K key) {

    }

    @Override
    public Iterator<K> iterator() {
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
