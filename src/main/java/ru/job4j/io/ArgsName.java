package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName  {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is empty. "
                    + "Usage java -jar argsname.jar ROOT_FOLDER"
            );
        }
        String[] x = args[0].split(",");
        if (x.length != 2) {
            throw new IllegalArgumentException("Root folder has to have two arguments. "
                    + "Usage java -jar argsname.jar ROOT_FOLDER"
            );
        }
        for (int i = 0; i < x.length; i++) {
            if (!x[i].startsWith("-encoding")) {
                if (!x[i].contains("=")) {
                    throw new IllegalArgumentException("Argument has to be equal to something. "
                            + "Usage java -jar argsname.jar ROOT_FOLDER"
                    );
                }
                    values.put(x[i].split("=")[0].split("-")[1], x[i].split("=")[1]);
                }
            }
        }

    public static ArgsName of(String[] args) {
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
