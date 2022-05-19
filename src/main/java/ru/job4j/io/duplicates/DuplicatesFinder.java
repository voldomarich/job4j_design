package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        search();
    }

    public static List<Path> search() throws IOException {
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
        return new DuplicatesVisitor().getPaths();
    }
}
