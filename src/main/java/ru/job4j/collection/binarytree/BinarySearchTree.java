package ru.job4j.collection.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        Objects.requireNonNull(key, "Ключ не может быть null");
        if (Objects.isNull(root)) {
            root = new Node(key);
            return true;
        } else {
            return put(root, key);
        }
    }

    private boolean put(Node node, T key) {
        while (true) {
            int currentValue = key.compareTo(node.key);
            if (currentValue < 0) {
                if (node.left == null) {
                    node.left = new Node(key);
                    return true;
                }
                node = node.left;
            } else if (currentValue > 0) {
                if (node.right == null) {
                    node.right = new Node(key);
                    return true;
                }
                node = node.right;
            } else {
                return false;
            }
        }
    }

    public boolean contains(T key) {
        Objects.requireNonNull(key, "Ключ не может быть null");
        return find(root, key) != null;
    }

    private Node find(Node node, T key) {
        while (node != null) {
            int currentValue  = key.compareTo(node.key);
            if (currentValue  < 0) {
                node = node.left;
            } else if (currentValue  > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    public boolean remove(T key) {
        /* Метод будет реализован в следующих уроках */
        return false;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        return inSymmetricalOrder(root, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> list = new ArrayList<>();
        return inPreOrder(root, list);
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            list.add(localRoot.key);
            inPreOrder(localRoot.left, list);
            inPreOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPostOrder() {
        List<T> list = new ArrayList<>();
        return inPostOrder(root, list);
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inPostOrder(localRoot.left, list);
            inPostOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
        return list;
    }

    public T minimum() {
        if (root == null) {
            throw new NoSuchElementException("Дерево пусто");
        }
        return minimum(root).key;
    }

    private Node minimum(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public T maximum() {
        if (root == null) {
            throw new NoSuchElementException("Дерево пусто");
        }
        return maximum(root).key;
    }

    private Node maximum(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return key.toString();
        }
    }
}
