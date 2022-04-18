package ru.job4j.io;

import java.io.*;
import java.util.Map;
import java.util.stream.Collectors;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            Map<String, String> a = in.lines()
                    .filter(s -> s.startsWith("400") || s.startsWith("500"))
                    .collect(Collectors.toMap(f -> f.split(" ")[0], f -> f.split(" ")[1]));
            Map<String, String> b = in.lines()
                    .filter(s -> s.startsWith("200") || s.startsWith("300"))
                    .collect(Collectors.toMap(f -> f.split(" ")[0], f -> f.split(" ")[1]));
            for (String key : a.keySet()) {
                for (String k : b.keySet()) {
                    target = a.get(key) + ";" + b.get(k);
                }
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
