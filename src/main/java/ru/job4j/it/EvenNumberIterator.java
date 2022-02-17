package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumberIterator implements Iterator<Integer> {

    private final int[] data;
    private int index;

    public EvenNumberIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i : data) {
            if (i % 2 == 0) {
                i = index;
            }
        }
        return index % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
