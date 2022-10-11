package ru.job4j.a;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;
import ru.job4j.io.Zip;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Searcher {

    public static boolean validation(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Root folder has to have two arguments. "
                    + "Usage java -jar search.jar ROOT_FOLDER .JS"
            );
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!args[3].endsWith(".txt")) {
            throw new IllegalArgumentException("Root folder has to have argument of format .txt "
                    + "Searcher java -jar searcher.jar ROOT_FOLDER .txt");
        }
        return true;
    }

    public static List<Path> search(Path root) throws IOException {
        FileSearcher fileSearcher = new FileSearcher();
        Files.walkFileTree(root, fileSearcher);
        return fileSearcher.getPaths();
    }

    public static List<Path> searchByName(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        if (validation(args)) {
            ArgsName argsName = ArgsName.of(args);
            Path start = Paths.get(argsName.get("d"));
            List<Path> result = search(start);
            System.out.println(result);

            if (argsName.get("t").equals("name")) {

                List<Path> resultByName = searchByName(start,
                        p -> p.toFile().getName().contains(argsName.get("n")));
                System.out.println(resultByName);
            }
            File searcherResult = new File(argsName.get("o"));
            Zip zip = new Zip();
            zip.packFiles(result, searcherResult);
        }
    }
}
