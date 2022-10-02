package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    public void createTable(String tableName) throws Exception {
        try (Connection connection = DriverManager.getConnection("url", "login", "password")) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(tableName);
            }
        }
    }

    public static void dropTable(String tableName) throws Exception {
        try (Connection connection = DriverManager.getConnection("url", "login", "password")) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(tableName);
                System.out.println("Table is deleted");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Connection connection = DriverManager.getConnection("url", "login", "password")) {
            try (Statement statement = connection.createStatement()) {
                int column = statement.executeUpdate("Add FROM tableName WHERE Id = columnName");
                System.out.printf("%d column(s) added", column);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Connection connection = DriverManager.getConnection("url", "login", "password")) {
            try (Statement statement = connection.createStatement()) {
                int column = statement.executeUpdate("DELETE FROM tableName WHERE Id = columnName");
                System.out.printf("%d column(s) deleted", column);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        try (Connection connection = DriverManager.getConnection("url", "login", "password")) {
            try (Statement statement = connection.createStatement()) {
                int column = statement.executeUpdate("UPDATE tableName SET columnName = newColumnName");
                System.out.printf("%d column(s) renamed", column);
            } catch (Exception e) {
                System.out.println("Connection failed...");
                System.out.println(e);
            }
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println();
    }
}
