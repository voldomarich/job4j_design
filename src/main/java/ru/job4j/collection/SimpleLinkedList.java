package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    private E[] container;

    private int size;

    private int modCount;

    @Override
    public void add(E value) {
        container = Arrays.copyOf(container, container.length);
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public Iterator<E> iterator() {

         int index;

         final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return index < size - 1;
        }

        @Override
        public E next() {
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
