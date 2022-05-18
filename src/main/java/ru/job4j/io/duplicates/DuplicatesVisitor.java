package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        int countFileProperty = 0;
        FileProperty fileProperty = null;
        if (file.equals(fileProperty.getName())) {
            countFileProperty++;
        }
        if (file.getNameCount() == countFileProperty) {
            return super.visitFile(file, attrs);
        }
        return FileVisitResult.CONTINUE;
    }
}
