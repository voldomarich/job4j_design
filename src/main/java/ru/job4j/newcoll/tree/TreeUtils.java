package ru.job4j.newcoll.tree;

import ru.job4j.collection.Queue;
import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.*;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException, если root является null
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
     * @throws IllegalArgumentException, если root является null
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

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException, если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {

        boolean result = false;
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException("Корень равен null");
        }
        Optional<Node<T>> optionalParent = findByKey(root, parent);
        Optional<Node<T>> optionalChild = findByKey(root, child);
        if (optionalParent.isPresent()) {
            Node<T> node = optionalParent.get();
            if (optionalChild.isEmpty()) {
                node.getChildren().add(new Node<>(child));
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException, если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {

        Optional<Node<T>> result = Optional.empty();
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException("Корень равен null");
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            if (Objects.equals(currentNode.getValue(), key)) {
                result = Optional.of(currentNode);
            }
            for (Node<T> child : currentNode.getChildren()) {
                stack.push(child);
            }
        }
        return result;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException, если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {

        Optional<Node<T>> result = Optional.empty();
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException("Корень равен null");
        }
        if (Objects.equals(root.getValue(), key)) {
            result = Optional.of(root);
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            for (Iterator<Node<T>> iterator = currentNode.getChildren().iterator(); iterator.hasNext();) {
                Node<T> child = iterator.next();
                if (Objects.equals(child.getValue(), key)) {
                    iterator.remove();
                    result = Optional.of(child);
                }
                stack.push(child);
            }
        }
        return result;
    }
}
