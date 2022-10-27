package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        List<String> result = new ArrayList<>();
        StringBuilder rsl = new StringBuilder();
        String[] names = argsName.get("filter").split(",");
        try (BufferedReader in = new BufferedReader(new FileReader(argsName.get("path")))) {
            String line = in.readLine();
            String[] columns = line.split(argsName.get("delimiter"));
            int[] indexes = new int[names.length];
            int count = 0;
            for (String name : names) {
                for (int i = 0; i < columns.length; i++) {
                    if (name.equals(columns[i])) {
                        indexes[count++] = i;
                    }
                }
            }
            while (in.ready()) {
                line = in.readLine();
                String[] str = line.split(argsName.get("delimiter"));
                rsl.append(argsName.get("filter")).append(argsName.get("delimiter"));
                for (int index : indexes) {
                    rsl.append(str[index]).append(argsName.get("delimiter"));
                }
                result.add(rsl.toString());
            }
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("out"))
                ))) {
            result.forEach(out::println);
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
        if (argsName.get("filter").contains("")) {
            throw new IllegalArgumentException("Ключ параметра filter не содержит значения");
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
