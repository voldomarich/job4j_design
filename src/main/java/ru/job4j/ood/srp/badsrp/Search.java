package ru.job4j.ood.srp.badsrp;

import ru.job4j.io.SearchFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/* Предикат необходимо прописывать в параметрах метода, чтобы его можно было изменять извне */
public class Search {
    public static List<Path> search(Path root) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith("a"));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
