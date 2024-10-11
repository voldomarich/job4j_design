package ru.job4j.newcoll.tree;

import ru.job4j.collection.Queue;
import ru.job4j.collection.SimpleQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException();
        }
        int result = 0;
        Queue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        while (queue.size() > 0) {
            Node<T> node = queue.poll();
            node.getChildren().forEach(queue::push);
            result++;
        }
        return result;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException();
        }
        List<T> result = new ArrayList<>();
        Queue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        while (queue.size() > 0) {
            Node<T> node = queue.poll();
            result.add(node.getValue());
            node.getChildren().forEach(queue::push);
        }
        return result;
    }
}
