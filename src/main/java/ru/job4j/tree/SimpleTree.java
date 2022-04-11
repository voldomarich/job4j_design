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
        boolean rsl = false;
        Optional<Node<E>> opt = findBy(parent);
        Optional<Node<E>> opt2 = findBy(child);
        if (opt.isPresent()) {
            Node<E> node = opt.get();
            if (opt2.isEmpty()) {
                node.children.add(new Node<>(child));
                rsl = true;
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        Predicate<Node<E>> pred = f -> f.children.size() > 2;
        return findByPredicate(pred).isEmpty();
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
        Predicate<Node<E>> pred = f -> f.value.equals(value);
        return findByPredicate(pred);
    }
}
