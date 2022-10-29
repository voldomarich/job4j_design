package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class CSVReaderTest {

    @Test
    void whenFilterTwoColumns(@TempDir Path folder) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = folder.resolve("source.csv").toFile();
        File target = folder.resolve("target.csv").toFile();
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=" + target.getAbsolutePath(), "-filter=name,education"};
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;education",
                "Tom;Bachelor",
                "Jack;Undergraduate",
                "William;Secondary special"
        ).concat(System.lineSeparator());
        CSVReader.handle(args);
        assertThat(Files.readString(target.toPath())).isEqualTo(expected);
    }

    @Test
    void whenFilterThreeColumns(@TempDir Path folder) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = folder.resolve("source.csv").toFile();
        File target = folder.resolve("target.csv").toFile();
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=" + target.getAbsolutePath(), "-filter=education,age,last_name"};
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "education;age;last_name",
                "Bachelor;20;Smith",
                "Undergraduate;25;Johnson",
                "Secondary special;30;Brown"
        ).concat(System.lineSeparator());
        CSVReader.handle(args);
        assertThat(Files.readString(target.toPath())).isEqualTo(expected);
    }

    @Test
    void whenFilterHasNoColumns(@TempDir Path folder) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = folder.resolve("source.csv").toFile();
        File target = folder.resolve("target.csv").toFile();
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=" + target.getAbsolutePath(), "-filter="};
        Files.writeString(file.toPath(), data);
        assertThatThrownBy(() -> CSVReader.handle(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Входной параметр не содержит значения");
    }

    @Test
    void whenOutHasNoValue(@TempDir Path folder) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = folder.resolve("source.csv").toFile();
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=", "-filter="};
        Files.writeString(file.toPath(), data);
        assertThatThrownBy(() -> CSVReader.handle(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Входной параметр не содержит значения");
    }

    @Test
    void whenArgsHasNoValues(@TempDir Path folder) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = folder.resolve("source.csv").toFile();
        String[] args = new String[]{};
        Files.writeString(file.toPath(), data);
        assertThatThrownBy(() -> CSVReader.handle(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Root folder is empty.");
    }
}
