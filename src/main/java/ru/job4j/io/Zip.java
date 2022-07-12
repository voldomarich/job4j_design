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
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean validation(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Корневая папка пуста "
                    + "Usage java -jar argsname.jar ROOT_FOLDER"
            );
        }
        ArgsName arguments = ArgsName.of(args);
        File file = new File(arguments.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        if (validation(args)) {
            ArgsName arguments = ArgsName.of(args);
            List<Path> result = new LinkedList<>(Search.search(Path.of(arguments.get("d")),
                    p -> !p.toFile().getName().endsWith(arguments.get("e"))));
            File target = new File(arguments.get("o"));
            Zip zip = new Zip();
            zip.packFiles(result, target);
        }
    }
}
