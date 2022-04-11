package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value, head);
        /*
         создаём новый узел, в который записываем
        значение value и ссылку на предыдущий элемент;
        */
        head = node;             /*  головой списка становится узел node */
    }

    public T deleteFirst() {
        if (head == null) {      /* если голова списка равна null - метод выбрасывает исключение; */
            throw new NoSuchElementException();
        }
        T result = head.value;  /* сохраняем текущее значение head в переменной;  */
        Node<T> node = head.next;  /* сохраняем следующий (поступил перед) элемент в узле node; */
        head.value = null; /* обнуляем текущее значение head; */
        head.next = null;  /* обнуляем ссылку головы на следующий элемент; */
        head = node;   /*  элемент node, который был следующим - становится головой списка head  */
        return result;   /* метод возвращает сохраненное значение удаленной головы  */
    }

    public boolean revert() {
        if (head == null || head.next == null) { /* список пуст или в списке один элемент;  */
            return false;
        }
        Node<T> next = head.next; /* создаем следующий элемент; */
        head.next = null; /* предыдущий = null; обнуляем ссылки на предыдущий элемент */
        while (next != null) {  /* выполняем перестановку индексов до тех пор,
        пока не переберём все элементы в списке, то есть пока следующий элемент не будет равен null */
            Node<T> last = next.next;   /* завершающий = текущий.следующий // 1 = 2.следующий   */
            next.next = head;    /* текущий.следующий = предыдущий; // 2.следующий = 1   */
            head = next;         /* предыдущий = текущий;  //    1 = текущий;    */
            next = head.next;    /* текущий = следующий;  //     1 = следующий;  */
            next = last;        /* голова = предыдущий; //      голова = 2;     */
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
