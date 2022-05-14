package ru.job4j.inheritance;

public class PizzaExtraCheeseExtraTomato extends PizzaExtraCheese {

    public final static String PECET_NAME = " plus tomatoes";

    @Override
    public String name() {
        return super.name() + PECET_NAME;
    }
}
