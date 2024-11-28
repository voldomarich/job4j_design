package ru.job4j.collection.binarytree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        System.out.println(tree);
        System.out.println(tree.remove(6));
        System.out.println("После удаления узла 6 :");
        System.out.println(tree);
    }
}
