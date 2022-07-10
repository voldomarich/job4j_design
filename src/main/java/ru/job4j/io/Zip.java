package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> result, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            for (Path path : result) {
                    zip.putNextEntry((ZipEntry) path);
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(path)))) {
                        zip.write(out.readAllBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) throws IOException {
        List<Path> result = new LinkedList<>();
        if (args.length == 0) {
            throw new IllegalArgumentException("Корневая папка пуста "
                    + "Usage java -jar argsname.jar ROOT_FOLDER"
            );
        }
        ArgsName arguments = ArgsName.of(args);
        result.addAll(Search.search(Path.of(arguments.get("d")),
                p -> !p.toFile().getName().endsWith(arguments.get("e"))));
        File target = new File(arguments.get("o"));
    }
}
