package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties config;
    private final String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            for (String string : rd.lines().toList()) {
                String[] s = string.split(";");
                if (s.length != 2) {
                    throw new IllegalArgumentException("Входных параметра должно быть два");
                }
                if (string.split(";")[0].isEmpty()) {
                    throw new IllegalArgumentException("Имя и фамилия пользователя отсутствуют");
                }
                if (string.split(";")[1].isEmpty()) {
                    throw new IllegalArgumentException("Электронная почта пользователя отсутствует");
                }
            }
            User user = new User(rd.readLine().split(";")[0], rd.readLine().split(";")[1]);
            users.add(user);
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement =
                        connection.prepareStatement("INSERT INTO users(name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream inputStream = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(inputStream);
        }
        ImportDB dataBase = new ImportDB(config, "./dump.txt");
        dataBase.save(dataBase.load());
    }
}
