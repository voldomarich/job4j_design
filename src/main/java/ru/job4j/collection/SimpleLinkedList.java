package ru.job4j.collection;

import org.w3c.dom.*;

import java.util.*;

public class SimpleLinkedList<E> implements List<E>, Iterable<E> {

    E item;
    Node<E> prev;
    Node<E> next;

    private int modCount;

    private Node<E> node;
    private int size = 0;

    public SimpleLinkedList() {
        node = new Node<E>(null, prev, next);
    }

    @Override
    public void add(E value) {
        Node<E> a = node;
        a.setNodeValue(value);
        node = new Node<E>(null, prev, next);
        a.setPrefix(next);
        size++;
    }

    @Override
    public E get(int index) {
        Node<E> target = node.getNextSibling();
        for (int i = 0; i < index; i++) {
            target = get(target);
        }
        return target.getNextSibling();
    }

    @Override
    public Iterator<E> iterator() {

         int count = 0;

         final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return get(count++);

        }
    };
}
