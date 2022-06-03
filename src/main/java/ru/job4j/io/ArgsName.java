package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Корневая папка не содержит аргументов"
            );
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String s : args) {
            if (!s.startsWith("-")) {
                throw new IllegalArgumentException("Параметр не соответствует формату, должен начинаться со знака -"
                );
            }
            if (!s.contains("=")) {
                throw new IllegalArgumentException("Параметр не содержит либо ключа, либо значения"
                );
            }
            if (s.startsWith("-=")) {
                throw new IllegalArgumentException("Параметр не содержит ключа"
                );
            }
            String[] string = s.split("=", 2);
            if (string[1].isEmpty()) {
                throw new IllegalArgumentException("Параметр не содержит значения"
                );
            }
            if (s.contains(string[0])) {
                throw new IllegalArgumentException("Несколько одинаковых ключей"
                );
            }
                values.put(string[0].substring(1), string[1]);
            }
        }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is empty. "
                    + "Usage java -jar argsname.jar ROOT_FOLDER"
            );
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.print(jvm.get("Xmx") + ", ");
        System.out.println(jvm.get("encoding"));
        System.out.println();

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.print(zip.get("out") + ", ");
        System.out.println(zip.get("encoding"));
    }
}
