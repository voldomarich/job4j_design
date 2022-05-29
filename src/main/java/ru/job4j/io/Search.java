package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static boolean validation(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder is null. "
                    + "Usage java -jar search.jar ROOT_FOLDER.");
        }
            File file = new File(args[0]);
            if (!file.isFile()) {
                throw new IllegalArgumentException("Root folder is not file. "
                        + "Usage java -jar search.jar ROOT_FOLDER.");
            }
            if (!file.isDirectory()) {
                throw new IllegalArgumentException("Root folder is not directory. "
                        + "Usage java -jar search.jar ROOT_FOLDER.");
            }
            if (!args[1].startsWith(".")) {
                throw new IllegalArgumentException("Root folder only has one argument "
                        + "and has to have one more argument. "
                        + "Usage java -jar search.jar ROOT_FOLDER.");
            }
            return true;
        }

    public static void main(String[] args) throws IOException {
        if (validation(args)) {
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
