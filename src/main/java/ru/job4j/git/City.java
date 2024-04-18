package ru.job4j.git;

import java.util.Date;
import java.util.Objects;

public class City {
    private String name;

    private int age;

    private Date birthday;

    public City() {
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
        City city = (City) o;
        return Objects.equals(name, city.name) && Objects.equals(birthday, city.birthday);
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
