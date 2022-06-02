package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName  {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Корневая папка не содержит аргументов"
            );
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        String[] array = args[0].split(",");
        for (String s : array) {
            if (!s.startsWith("-")) {
                throw new IllegalArgumentException("Параметр не соответствует формату"
                );
            }
            if (!s.contains("=")) {
                throw new IllegalArgumentException("Параметр не содержит либо ключа, либо значения"
                );
            }
            if (s.endsWith("=")) {
                throw new IllegalArgumentException("Параметр не содержит значения"
                );
            }
            if (s.startsWith("-=")) {
                throw new IllegalArgumentException("Параметр не соответствует формату"
                );
            }
            String[] x = s.split("=");
            if (x[1] != null) {
                values.put(x[0], x[1]);
            }
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
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
