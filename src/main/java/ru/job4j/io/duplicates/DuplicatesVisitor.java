package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private List<Path> result = new ArrayList<>();
    private int count = 0;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        FileProperty fileProperty = new FileProperty(file.toFile().getTotalSpace(),
                file.toFile().getName());
        count++;
        Set<FileProperty> set = new HashSet<>();
        set.add(fileProperty);
        if (set.size() != count) {
            for (FileProperty f : set) {
                result.add(f.toPath);
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return result;
    }
}
