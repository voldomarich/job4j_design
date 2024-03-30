package ru.job4j.ood.ocp.badocp;

import ru.job4j.gc.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/* Properties лучше принимать в качестве параметра класса,
поэтому нужно создать поле и конструктор,
где будет происходить инициализация поля.
Поля и параметры методов должны представлять собой тип абстракций
 */
public class ReportA {

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Properties config = new Properties();
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO users(name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.getName());
                    preparedStatement.setInt(2, user.getScore());
                    preparedStatement.execute();
                }
            }
        }
    }
}
