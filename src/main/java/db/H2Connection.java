package db;

import java.sql.*;

public class H2Connection {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:parser-stat");
    }

    private void executor(String query) throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.execute(query);
    }

    public void prepareTable() throws SQLException {
        executor("CREATE TABLE HTML_STAT (id INTEGER PRIMARY KEY AUTO_INCREMENT, word VARCHAR(255), count INTEGER, site VARCHAR(255))");
    }

    public void fillTable(String text, int count,H2Connection connection) throws SQLException {
        PreparedStatement statement = connection
                                        .getConnection()
                                        .prepareStatement("INSERT INTO HTML_STAT(word,count,site) VALUES (?,?,?)");

        statement.setString(1,text);
        statement.setInt(2,count);
        statement.setString(3,"simbirsoft");

        statement.execute();
    }

    public void showTableInfo() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM HTML_STAT");

        while (rs.next())
            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
    }
}
