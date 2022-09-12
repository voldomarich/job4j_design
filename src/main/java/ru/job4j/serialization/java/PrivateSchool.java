package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class PrivateSchool {

    private final boolean category;
    private final int age;
    private final String name;
    private final Professor professor;
    private final String[] scholars;

    public PrivateSchool(boolean category, int age, String name, Professor professor, String[] scholars) {
        this.category = category;
        this.age = age;
        this.name = name;
        this.professor = professor;
        this.scholars = scholars;
    }

    @Override
    public String toString() {
        return "PrivateSchool{"
                + "category=" + category
                + ", age=" + age
                + ", name=" + name
                + ", professor=" + professor
                + ", scholars=" + Arrays.toString(scholars)
                + '}';
    }

    public static void main(String[] args) {
        final PrivateSchool privateSchool = new PrivateSchool(true, 100,
                "Школа сотрудничества", new Professor(35, "Alexey"),
                new String[]{"German", "Vladimir", "Victoria"});

        /* Преобразуем объект privateSchool в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(privateSchool));

        /* Модифицируем json-строку */
        final String schoolJson =
                "{"
                        + "\"category\":true,"
                        + "\"age\":32,"
                        + "\"name\":Школа сотрудничества,"
                        + "\"professor\":"
                        + "{"
                        + "\"age\":34,"
                        + "\"name\":Vladimir"
                        + "},"
                        + "\"scholars\":"
                        + "[\"Ivan\",\"Alexey\",\"Alexandra\"]"
                        + "}";
        final PrivateSchool schoolMod = gson.fromJson(schoolJson, PrivateSchool.class);
        System.out.println(schoolMod);
    }
}
