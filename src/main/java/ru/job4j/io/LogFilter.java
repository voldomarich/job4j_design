package ru.job4j.io;

import java.io.*;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().filter("404"::contains).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return in;
        }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("log.txt")
                ))) {
            out.println(log);
        } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "log.txt");
    }
}
