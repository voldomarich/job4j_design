package ru.job4j.serialization;

public class Teacher {

    private final String sex;
    private final int age;
    private final String name;

    public Teacher(String sex, int age, String name) {
        this.sex = sex;
        this.age = age;
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
