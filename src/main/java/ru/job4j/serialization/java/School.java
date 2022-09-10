package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class School {

    private final boolean category;
    private final int age;
    private final Teacher teacher;
    private final String[] scholars;

    public School(boolean category, int age, Teacher teacher, String[] scholars) {
        this.category = category;
        this.age = age;
        this.teacher = teacher;
        this.scholars = scholars;
    }

    @Override
    public String toString() {
        return "School{"
                + "category=" + category
                + ", age=" + age
                + ", teacher=" + teacher
                + ", scholars=" + Arrays.toString(scholars)
                + '}';
    }

    public static void main(String[] args) {
        final School school = new School(true, 100, new Teacher("male", 35, "Alexey"),
                new String[] {"German", "Vladimir", "Victoria"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(school));

        /* Модифицируем json-строку */
        final String schoolJson =
                "{"
                        + "\"category\":true,"
                        + "\"age\":100,"
                        + "\"teacher\":"
                        + "{"
                        + "\"sex\":male,"
                        + "\"age\":34,"
                        + "\"name\":Vladimir"
                        + "},"
                        + "\"scholars\":"
                        + "[\"Ivan\",\"Alexey\",\"Alexandra\"]"
                        + "}";
        final School schoolMod = gson.fromJson(schoolJson, School.class);
        System.out.println(schoolMod);
    }
}
