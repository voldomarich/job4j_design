package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        List<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (!sc.toString().contains(OUT)) {
            if (sc.toString().contains(STOP)) {
                while (!sc.toString().contains(CONTINUE)) {
                    list.add(sc.toString());
                    saveLog(list);
                }
            }
            list.add(sc.toString());
            list.add(readPhrases().stream().findFirst().get());
        }
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path,
                Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("path", "botAnswers");
        cc.run();
    }
}
