package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void drop() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("new.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("""
                    200 10:56:01
                    200 10:57:01
                    400 10:58:01
                    300 10:58:45
                    200 10:59:01
                    500 11:01:02
                    200 11:02:02
                    400 11:02:29""");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
            assertThat(rsl.toString(), is("10:58:01; 10:58:45\n"
                    + "11:01:02; 11:02:02"));
        }
    }
}
