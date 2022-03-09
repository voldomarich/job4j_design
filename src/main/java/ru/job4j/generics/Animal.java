package ru.job4j.generics;

import java.util.Date;
import java.util.Objects;

public class Animal {

    private String name;

    private int age;

    private Date birthday;

    public Animal() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name) && Objects.equals(birthday, animal.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }

    @Override
    public String toString() {
        return "Animal{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", birthday=" + birthday
                + '}';
    }
}
