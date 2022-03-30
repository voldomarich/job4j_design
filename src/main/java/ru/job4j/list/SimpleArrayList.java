package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            expand(container);
            container.add(value);
            size++;
            modCount++;
        }
        container.add(value);
        size++;
        modCount++;
    }

    @Override
    public T[] expand(T[] container) {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = container[index];
        Objects.checkIndex(index, container.length);
        container[index] = newValue;
        modCount++;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T oldValue = container[index];
        Objects.checkIndex(index, container.length);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return container.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int index;

            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return index < size - 1;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];

            }
        };
    }
}
