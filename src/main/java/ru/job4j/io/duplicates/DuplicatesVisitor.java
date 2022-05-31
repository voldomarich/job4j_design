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

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        FileProperty fileProperty = new FileProperty(file.toFile().getTotalSpace(),
                file.toFile().getName());
        List<FileProperty> list = new ArrayList<>();
        list.add(fileProperty);
        Set<FileProperty> set = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            FileProperty f = list.get(i);
            if (f == null) {
                continue;
            }
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }
                FileProperty f2 = list.get(j);
                if (f.equals(f2)) {
                    set.add(f2);
                }
            }
            result.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return result;
    }
}
