package ru.job4j.jdbc;

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
        connection = DriverManager.getConnection(properties.getProperty("hibernate.connection.url"),
                properties.getProperty("hibernate.connection.login"),
                properties.getProperty("hibernate.connection.password"));
    }

    private void getStatement(String query, String tableName) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            if (!query.startsWith("drop")) {
                System.out.println(getTableScheme(tableName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format("CREATE table %s (id serial primary key);", tableName);
        getStatement(sql, tableName);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("DROP table %s;", tableName);
        getStatement(sql, tableName);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("ALTER table %s ADD %s %s NULL;", tableName, columnName, type);
        getStatement(sql, tableName);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("ALTER table %s DROP COLUMN %s;", tableName, columnName);
        getStatement(sql, tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format("ALTER table %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName);
        getStatement(sql, tableName);
    }

    public String getTableScheme(String tableName) throws Exception {
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
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("table.properties")) {
            properties.load(in);
            try (TableEditor tableEditor = new TableEditor(properties)) {
                tableEditor.createTable("caars");
                tableEditor.addColumn("caars", "engine", "text");
                tableEditor.addColumn("caars", "bodies", "text");
                tableEditor.addColumn("caars", "transmission", "text");
                tableEditor.dropColumn("caars", "transmission");
                tableEditor.renameColumn("caars", "bodies", "body");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
