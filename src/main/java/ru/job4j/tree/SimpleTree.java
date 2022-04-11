package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {

    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = true;
        if (findBy(parent).isPresent()) {
            Node<E> root = findBy(parent).get();
            for (Node<E> kid : root.children) {
                if (kid.value.equals(child)) {
                    rsl = false;
                    break;
                }
                root.children.add(new Node<>(child));
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean rsl = false;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> root = data.poll();
            if (root.children.size() < 3) {
                    rsl = true;
                }
        }
        return rsl;
    }

    public Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> root = data.poll();
            for (Node<E> kid : root.children) {
                if (condition.test(kid)) {
                    data.add(kid);
                }
            }
        }
            return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
