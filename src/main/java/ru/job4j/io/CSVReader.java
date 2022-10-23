package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        List<String> result = new ArrayList<>();
        String[] names = argsName.get("filter").split(",");
        try (BufferedReader in = new BufferedReader(new FileReader(argsName.get("path")))) {
            String firstLine = in.readLine().split(argsName.get("delimiter"), 3)[0];
            String[] columns = firstLine.split(argsName.get("delimiter"));
            int[] indexes = new int[names.length];
            int count = 0;
            for (String name : names) {
                for (int j = 0; j < columns.length; j++) {
                    if (name.equals(columns[j])) {
                        indexes[count++] = j;
                    }
                }
            }
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("out"))
                ))) {
            result.forEach(out::println);
            var scanner = new Scanner(new ByteArrayInputStream(argsName.get("filter").getBytes()))
                    .useDelimiter(argsName.get("delimiter"));
            System.out.println(out);
            System.out.println(scanner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean validation(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException("Root folder is supposed to have four arguments. "
                    + "Usage java -jar search.jar ROOT_FOLDER .JS"
            );
        }
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Root folder is supposed to have argument of format .csv "
                    + "Usage java -jar search.jar ROOT_FOLDER .JS");
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        if (args.length == 0) {
            throw new IllegalArgumentException("Корневая папка пуста "
                    + "Usage java -jar argsname.jar ROOT_FOLDER"
            );
        }
        if (validation(args)) {
            handle(argsName);
        }
    }
}
