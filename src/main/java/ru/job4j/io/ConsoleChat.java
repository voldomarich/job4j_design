package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        saveLog(readPhrases());
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.add(builder.toString());
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(botAnswers, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
            if (path.contains(OUT)) {
                pw.flush();
            }
            if (path.contains(CONTINUE)) {
                run();
            }
            if (path.contains(STOP)) {
                pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("path", "botAnswers");
        cc.run();
    }
}
