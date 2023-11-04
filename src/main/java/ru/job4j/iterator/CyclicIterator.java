package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private final List<T> data;
    private int index = 0;
    /* здесь разместите поля класса, если они будут нужны  */

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return index < data.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T result = data.get(index);
        for (int i = index; i < data.size(); i++) {
            if (i != data.size() - 1) {
                result = data.get(i);
            } else {
                result = data.get(i);
                i = 0;
            }
        }
        return result;
    }
}
