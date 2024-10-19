package ru.job4j.collection.binarytree;

public class Node implements VisualNode {

    private final int key;
    private Node left;
    private Node right;

    public Node(int key) {
        this.key = key;
    }

    public Node(int key, Node left, Node right) {
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
        return String.valueOf(key);
    }
}
