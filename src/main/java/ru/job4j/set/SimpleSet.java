package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        int size = set.size();
        set.add(value);
        return set.size() > size;
    }

    @Override
    public boolean contains(T value) {
        for (T s : set) {
            return value.equals(s);
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
