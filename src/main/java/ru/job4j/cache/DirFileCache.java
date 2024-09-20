package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
        System.out.printf("CACHED DIR HAS BEEN CREATED, LOCATION IS %s%n", cachingDir);
    }

    @Override
    protected String load(String key) {
        StringJoiner stringJoiner = new StringJoiner("/");
        stringJoiner.add(cachingDir).add(key);
        String path = stringJoiner.toString();

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path,
                StandardCharsets.UTF_8))) {
            for (String string = bufferedReader.readLine(); string != null;
                 string = bufferedReader.readLine()) {
                stringBuilder.append(string);
                stringBuilder.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String loaded = stringBuilder.toString();
        put(key, loaded);
        return loaded;
    }
}
