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
            throw new IllegalArgumentException("Root folder has to have two arguments"
                    + "Usage java -jar search.jar ROOT_FOLDER .JS"
                    );
        }
            File file = new File(args[0]);
            if (!file.isFile()) {
                throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
            }
            if (!file.isDirectory()) {
                throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
            }
            if (!args[1].endsWith(".js")) {
                throw new IllegalArgumentException("Root folder has to have argument of format .js "
                        + "Usage java -jar search.jar ROOT_FOLDER .JS");
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
