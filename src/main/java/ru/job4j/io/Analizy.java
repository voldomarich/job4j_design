package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            List<String> result = in.lines()
                    .collect(Collectors.toList());
            StringBuilder rsl = new StringBuilder();
            for (String i : result) {
                if (i.startsWith("400") || i.startsWith(("500"))) {
                    rsl.append(i.split(" ")[1] + ";");
                }
                if (i.startsWith("200") || i.startsWith("300")) {
                    rsl.append(i.split(" ")[1]);
                }
                break;
            }
            target = rsl.toString();
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(target)
                    ))) {
                out.println(out);
                System.out.println(out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void main(String[] args) {
        unavailable("server.log", "unavailable.csv");
    }
}
