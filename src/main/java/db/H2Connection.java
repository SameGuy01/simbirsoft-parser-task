package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Connection {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:parser-stat");
    }

    private void executeUpdate(String query) throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(query);
    }

    public void createHtmlStatTable() throws SQLException {
        String htmlStatQuery = "CREATE TABLE HTML_STAT " +
                "(id INTEGER PRIMARY KEY, name TEXT)";
        String htmlEntryQuery = "INSERT INTO HTML_STAT " +
                "VALUES (1, 'simbirsoft')";
        executeUpdate(htmlStatQuery);
        executeUpdate(htmlEntryQuery);
    }

    private void selectData() throws SQLException {
        createHtmlStatTable();
        String query = "SELECT * FROM html_stat";

    }
}
