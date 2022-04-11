package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        if (!set.contains(value)) {
            set.add(value);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        boolean x = false;
        for (T s : set) {
            if (s.equals(value)) {
                x = true;
                break;
            }
        }
        return x;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
