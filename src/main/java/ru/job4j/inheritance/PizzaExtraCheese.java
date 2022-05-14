package ru.job4j.inheritance;

public class PizzaExtraCheese extends Pizza {

    private static final String PEC_NAME = " plus extra cheese";

    @Override
    public String name() {
        return super.name() + PEC_NAME;
    }
}
