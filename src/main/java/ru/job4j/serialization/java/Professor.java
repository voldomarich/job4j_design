package ru.job4j.serialization.java;

public class Professor {

    private final int age;
    private final String name;

    public Professor(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
