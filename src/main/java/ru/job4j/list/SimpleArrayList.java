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
            expand();
            container[size] = value;
        }
        size++;
        modCount++;
    }

    private void expand() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length * 2 - container.length);
        T oldValue = container[index];
        container[index] = newValue;
        modCount++;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length * 2 - container.length);
        T oldValue = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length * 2 - container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int index;

            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];

            }
        };
    }
}
