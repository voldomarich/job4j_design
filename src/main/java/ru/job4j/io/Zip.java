package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    List<Path> result = new LinkedList<>();

    public void packFiles(List<File> sources, File target) throws IOException {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            for (Path path : result) {
                for (File source : sources) {
                    zip.putNextEntry(new ZipEntry((ZipEntry) path));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                        zip.write(out.readAllBytes());
                    }
                }
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Корневая папка пуста "
                    + "Usage java -jar argsname.jar ROOT_FOLDER"
            );
        }
        ArgsName arguments = ArgsName.of(args);
        result.addAll(Search.search(Path.of(arguments.get("d")),
                p -> !p.toFile().getName().endsWith(arguments.get("e"))));
        Zip zip = new Zip();
        zip.packSingleFile(
                new File(arguments.get("d")),
                new File(arguments.get("o"))
        );
    }
}
