package ru.job4j.serialization.java;

public record Professor(int age, String name) {

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
