package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String archive;
    private final String botAnswer;

    public ConsoleChat(String archive, String botAnswer) {
        this.archive = archive;
        this.botAnswer = botAnswer;
    }

    public void run() {
        List<String> bot = readPhrases();
        List<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        list.add(str);
        while (!OUT.equals(str)) {
            if (STOP.equals(str)) {
                while (!CONTINUE.equals(str)) {
                    str = sc.nextLine();
                    list.add(str);
                }
            }
            Random random = new Random();
            int index = random.nextInt(bot.size());
            String answer = bot.get(index);
            list.add(answer);
            System.out.println(answer);
            str = sc.nextLine();
            list.add(str);
        }
        saveLog(list);
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswer))) {
            br.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archive,
                StandardCharsets.UTF_16, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("archive", "botAnswer");
        cc.run();
    }
}
