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
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException("Root folder has to have two arguments. "
                    + "Usage java -jar search.jar ROOT_FOLDER .JS"
            );
        }
        File file = new File(argsName.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Root folder has to have argument of format .zip"
                    + "Searcher java -jar searcher.jar ROOT_FOLDER .zip");
        }
        return true;
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

            Predicate<Path> predicateByName = p -> p.toFile().getName().contains(argsName.get("n"));
            Predicate<Path> predicateByRegex = p -> p.toFile().getName().matches(argsName.get("n"));
            String mask = "^" + argsName.get("n").replace(".", "[.]")
                    .replace("*", ".*").replace("?", ".") + "$";
            Predicate<Path> predicateByMask = p -> p.toFile().getName().matches(mask);

            if (argsName.get("t").equals("name")) {
                List<Path> resultByName = searchByPredicate(Paths.get(argsName.get("d")), predicateByName);
                System.out.println(resultByName);
                zip.packFiles(resultByName, new File(argsName.get("o")));
            }
            if (argsName.get("t").equals("mask")) {
                List<Path> resultByMask = searchByPredicate(Paths.get(argsName.get("d")), predicateByMask);
                System.out.println(resultByMask);
                zip.packFiles(resultByMask, new File(argsName.get("o")));
            }
            if (argsName.get("t").equals("regex")) {
                List<Path> resultByRegex = searchByPredicate(Paths.get(argsName.get("d")), predicateByRegex);
                System.out.println(resultByRegex);
                zip.packFiles(resultByRegex, new File(argsName.get("o")));
            }
        }
    }
}
