package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Analizy {

    private Map<String, String> values = new HashMap<>();

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            values = in.lines()
                    .collect(Collectors.toMap((p) -> p.substring(0, 1), (p) -> p.substring(1, 2)))
                    .entrySet()
                    .stream()
                    .filter((s, v) -> s.equals("400") || s.equals("500"))
                    .collect(Collectors.toMap(f -> f.getKey(), f -> f.getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save(Map<String, String> values, String target) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (Map.Entry<String> line : values.entrySet()) {
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
