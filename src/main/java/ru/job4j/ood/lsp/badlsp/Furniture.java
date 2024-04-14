package ru.job4j.ood.lsp.badlsp;

public class Furniture {

    /* precondition: 0 < num <= 10 */
    public void doStuff(int num) {
        if (num <= 0 || num > 10) {
            throw new IllegalArgumentException("Input out of range 1-10");
        }
        /* some logic here... */
    }
}
