package ru.job4j.serialization.java;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "professor")
public class Professor {

    @XmlAttribute
    private final int age;

    @XmlAttribute
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

    @Override
    public String toString() {
        return "Professor{"
                + "age='" + age + '\''
                + ", name=" + name + '\''
                + '}';
    }
}
