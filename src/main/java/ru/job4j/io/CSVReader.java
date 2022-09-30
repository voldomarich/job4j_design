package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        List<String> result;
        if (validation(argsName)) {
            try (BufferedReader in = new BufferedReader(new FileReader(argsName.get("path")))) {
                result = in.lines().filter(a -> a.equals(argsName.get("filter")))
                .collect(Collectors.toList());
            }
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(argsName.get("stdout"))
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
    }

    private static boolean validation(ArgsName argsName) {
        if (argsName == null) {
            throw new IllegalArgumentException("Root folder has to have two arguments. "
                    + "Usage java -jar search.jar ROOT_FOLDER .JS"
            );
        }
        return true;
    }
}
