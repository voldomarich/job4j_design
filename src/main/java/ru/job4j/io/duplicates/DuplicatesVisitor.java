package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    List<Path> result = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        FileProperty fileProperty = new FileProperty(file.toFile().getTotalSpace(),
                file.toFile().getName());
        if (file.toFile().getName().equals(fileProperty.getName())) {
            result.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return result;
    }
}
