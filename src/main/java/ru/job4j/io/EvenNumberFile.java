package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int number;
            while ((number = in.read()) != -1) {
                text.append((char) number);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (number % 2 == 0) {
                    System.out.println(line + "чётное");
                } else {
                    System.out.println(line + "нечётное");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
