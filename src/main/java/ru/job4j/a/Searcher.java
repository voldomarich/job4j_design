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

    public static List<Path> searchByPredicate(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        
        if (validation(args)) {

            Zip zip = new Zip();
            ArgsName argsName = ArgsName.of(args);
            Path path = Paths.get(argsName.get("d"));

            List<Path> result = search(path);
            zip.packFiles(result, new File(argsName.get("o")));
            System.out.println(result);

            Predicate<Path> predicateByName = p -> p.toFile().getName().contains(argsName.get("n"));
            Predicate<Path> predicateByMask = p -> p.toFile().getName().endsWith(argsName.get("n"));
            Predicate<Path> predicateByRegex = p -> p.toFile().getName().contains("[");

            if (argsName.get("t").equals("name")) {
                List<Path> resultByName = searchByPredicate(path, predicateByName);
                System.out.println(resultByName);
                zip.packFiles(resultByName, new File(argsName.get("o")));
            }
            if (argsName.get("t").equals("mask")) {
                List<Path> resultByMask = searchByPredicate(path, predicateByMask);
                System.out.println(resultByMask);
                zip.packFiles(resultByMask, new File(argsName.get("o")));
            }
            if (argsName.get("t").equals("regex")) {
                List<Path> resultByRegex = searchByPredicate(path, predicateByRegex);
                System.out.println(resultByRegex);
                zip.packFiles(resultByRegex, new File(argsName.get("o")));
            }
        }
    }
}
