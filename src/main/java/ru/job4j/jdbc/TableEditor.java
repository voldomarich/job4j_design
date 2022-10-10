package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Config config = new Config("table.properties");
        config.load();
        connection = DriverManager.getConnection(properties.getProperty(config.value("hibernate.url")));
    }

    public void createStatement(String tableName) throws Exception {
        Config config = new Config("resources/table.properties");
        config.load();
        try (Connection connection =
                     DriverManager.getConnection(config.value("hibernate.url"),
                             config.value("hibernate.connection.login"),
                             config.value("hibernate.connection.password"))) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(tableName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createTable(String tableName) throws Exception {
        createStatement(tableName);
        System.out.println("Table is created");
    }

    public void dropTable(String tableName) throws Exception {
        createStatement(tableName);
        System.out.println("Table is deleted");
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        createStatement("Add FROM tableName WHERE Id = columnName");
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        createStatement("DELETE FROM tableName WHERE Id = columnName");
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        createStatement("UPDATE tableName SET columnName = newColumnName");
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
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("resources/table.properties")) {
            config.load(in);
            try (TableEditor tableEditor = new TableEditor(config)) {
                tableEditor.createTable("table");
                tableEditor.addColumn("table", "count", "type");
                tableEditor.dropColumn("table", "count");
                tableEditor.renameColumn("table", "count", "number");
                tableEditor.dropTable("table");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
