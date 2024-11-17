package ru.job4j.collection.binarytree;

public class Node<T> implements VisualNode {

    private T key;
    private Node left;
    private Node right;

    public Node(T key) {
        this.key = key;
    }

    public Node(T key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
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
