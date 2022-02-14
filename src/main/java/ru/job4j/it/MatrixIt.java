package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (row == data.length - 1 && data[row].length == 0
                || row == data.length - 1 && column >= data[row].length) {
            return false;
        }
        if (row != data.length - 1 && data[row].length == 0
        || row != data.length - 1 && column >= data[row].length) {
            row = row + 1;
            column = 0;
            return false;
            }
        return row < data.length;
        }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
