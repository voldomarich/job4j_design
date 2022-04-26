package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            List<String> result = in.lines()
                    .collect(Collectors.toList());
            StringBuilder rsl = new StringBuilder();
            boolean status = true;
            for (String i : result) {
                if ((i.startsWith("400") || i.startsWith("500"))
                        && !i.equals(result.get(result.size() - 1)) && status) {
                    status = false;
                    rsl.append(i.split(" ")[1]).append("; ");
                } else if ((i.startsWith("200") || i.startsWith("300")) && !status) {
                    status = true;
                    rsl.append(i.split(" ")[1]).append((System.lineSeparator()));
                    }
            }
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(target)
                    ))) {
                out.println(rsl);
                System.out.println(rsl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.csv");
    }
}
