package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        FileProperty fileProperty = new FileProperty(file.toFile().getTotalSpace(),
                file.toFile().getName());
        if (!map.containsKey(fileProperty)) {
            map.put(fileProperty, new ArrayList<>());
        }
        map.get(fileProperty).add(file);
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        for (FileProperty fileProperty : map.keySet()) {
            if (map.get(fileProperty).size() > 1) {
                return map.get(fileProperty);
            }
        }
        return null;
    }
}
