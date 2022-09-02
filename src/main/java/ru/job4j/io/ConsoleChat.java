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
        saveLog(readPhrases());
    }

    private List<String> readPhrases() {
        boolean status = true;
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        String userChat = "";
        writeDataInFile(userChat, scanner.toString());

        if (readFile(userChat).contains(STOP) && status) {
            run();
            status = false;

        } else if (readFile(userChat).contains(CONTINUE) && !status) {
            try (BufferedReader b = new BufferedReader(new FileReader(botAnswers))) {
                b.lines().map(s -> s + System.lineSeparator()).forEach(builder::append);
                status = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (readFile(userChat).contains(OUT) && status) {
            status = false;
        }
        result.add(builder.toString());
        result.add(userChat);
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private void writeDataInFile(String path, String data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            pw.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("path", "botAnswers");
        cc.run();
    }
}
