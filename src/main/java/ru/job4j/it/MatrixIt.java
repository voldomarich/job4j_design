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
        boolean rsl = false;
        for (int[] row : data) {
            for (int column : row) {
                if (row.length == 0 || column == row.length) {
                    row++;
                    rsl = true;
                }
            }
        }
        return rsl;

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
