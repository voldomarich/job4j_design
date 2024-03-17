package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {

    public static final Integer LOAD_FILE = 1;
    public static final Integer GET_FILE = 2;
    public static final Integer QUIT = 3;

    public static final String MENU = """
                Select-1 > load file into cache.
                Select-2 > get file.
                Select-3 > exit.
                """;

    public static final String SET = "input path to cached directory: ";
    public static final String GET = "input file name: ";
    public static final String LOAD = "input file name: ";
    public static final String EXIT = "exit";

    private static void start(Scanner scanner) {
        System.out.println(SET);
        String dir = scanner.nextLine();
        if (!Files.exists(Path.of(dir))) {
            System.out.println("folder does not exist. Restart again, please.");
        } else {
            DirFileCache textCache = new DirFileCache(dir);
            boolean run = true;

            while (run) {
                System.out.println(MENU);
                int userChoice = Integer.parseInt(scanner.nextLine());
                if (userChoice == LOAD_FILE) {
                    System.out.println(LOAD);
                    String file = scanner.nextLine();
                    textCache.load(file);
                }

                if (userChoice == GET_FILE) {
                    System.out.println(GET);
                    String file = scanner.nextLine();
                    System.out.println(textCache.get(file));
                }
                if (userChoice == QUIT) {
                    run = false;
                    System.out.println(EXIT);
                }
                if (userChoice != LOAD_FILE && userChoice != GET_FILE && userChoice != QUIT) {
                    System.out.println("wrong key");
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        start(scanner);
    }
}
