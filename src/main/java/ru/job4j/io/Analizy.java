package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Analizy {

    private Map<String, String> values = new HashMap<>();

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            values = in.lines()
                    .collect(Collectors.toMap((p) -> p.substring(0, 1), (p) -> p.substring(1, 2)))
                    .entrySet()
                    .stream()
                    .filter(s -> s.getKey().equals("400") || s.getKey().equals("500"))
                    .collect(Collectors.toMap(f -> f.getValue(), f -> f.getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save(Map<String, String> values, String target) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (var line : values.entrySet()) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
