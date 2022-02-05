package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private final int row = 0;
    private final int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return row < data.length && column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int a = 0;
        for (int i = row; i < data.length; i++) {
            for (int y = column; y < data[i].length; y++) {
                a = data[i][y];
            }
    }
        return a;
    }
}
