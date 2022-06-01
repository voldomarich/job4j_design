package ru.job4j.template;

import java.util.Map;

public class Compiler implements Generator {
    String produce(String template, Map<String, String> args);

}
