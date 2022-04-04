package ru.job4j.collection;

public interface List<E> extends Iterable<E> {

    void add(E value);
    E get(int index);

    class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
