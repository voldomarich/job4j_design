package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void validation(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar search.jar ROOT_FOLDER.");
        } else if (args.length == 1) {
            throw new IllegalArgumentException("Root folder only has one argument. Usage java -jar search.jar ROOT_FOLDER.");
        }
    }

    public static void main(String[] args) throws IOException {
        validation(args);
        Path start = Paths.get(args[1]);
        search(start, p -> p.toFile().getName().endsWith(args[0])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
