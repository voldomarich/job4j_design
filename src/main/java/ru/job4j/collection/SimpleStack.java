package ru.job4j.collection;

public class SimpleStack<T> implements Iterable<T> {

    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        return linked.addFirst(value);
    }
}
