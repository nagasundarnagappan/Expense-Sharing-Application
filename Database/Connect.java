package Database;

import Queries.*;

import java.sql.*;

public class Connect extends DatabaseDetails {
    private Connection conn = null;

    public Connect() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, getPassword());

            Statement stmt = conn.createStatement();

            stmt.executeUpdate(UserQueries.createTableIfNotExists());
            stmt.executeUpdate(ExpenseQueries.createTableIfNotExists());
            stmt.executeUpdate(SharesQueries.createTableIfNotExists());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return conn;
    }
}