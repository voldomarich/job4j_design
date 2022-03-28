package ru.job4j.collection;

import org.w3c.dom.*;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size = 0;

    public SimpleLinkedList() {
        firstNode = new Node<E>(null, firstNode, null);
        lastNode = new Node<E>(null, null, lastNode);
    }

    @Override
    public void add(E value) {
        Node<E> prev = firstNode;
        prev.setNodeValue(value);
        firstNode = new Node<E>(null, prev, null);
        prev.setPrefix(lastNode);
        size++;
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
