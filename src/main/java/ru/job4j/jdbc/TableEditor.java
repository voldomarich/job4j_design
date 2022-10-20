package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        connection = DriverManager.getConnection(properties.getProperty("hibernate.url"),
                properties.getProperty("hibernate.connection.login"),
                properties.getProperty("hibernate.connection.password"));
    }

    public void createTable(String tableName) throws Exception {
        String sql = """
                CREATE TABLE Student  \s
                ( \s
                Roll_No. Int ,   \s
                First_Name Varchar (20) ,   \s
                Last_Name Varchar (20) ,   \s
                Age Int , \s
                Marks Int ,  \s
                )""";
        System.out.println(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "DROP table %s NULL;", tableName);
        System.out.println(sql);
    }

    public void addColumn(String tableName, String columnName, String type, Connection conn) throws Exception {
        String sql = String.format(
                "ALTER table %s ADD %s %s NULL;", tableName, columnName, type);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "ALTER table %s, %s NULL;", tableName, columnName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "ALTER table %s, %s, %s NULL;", tableName, columnName, newColumnName);
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
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("table.properties")) {
            try (TableEditor tableEditor = new TableEditor(properties)) {
                tableEditor.createTable(String.format("%s", properties.getProperty("hibernate.url")));
                tableEditor.addColumn(properties.getProperty("hibernate.url"), "count");
                tableEditor.dropColumn("table", "count");
                tableEditor.renameColumn("table", "count", "number");
                tableEditor.dropTable("table");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
