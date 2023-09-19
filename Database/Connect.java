package Database;

import java.sql.*;

public class Connect {
    private Connection conn = null;
    DatabaseDetails dbDetails = new DatabaseDetails();

    public Connect() {
        try {
            Class.forName(dbDetails.getDriver());
            conn = DriverManager.getConnection(dbDetails.getUrl(), dbDetails.getUsername(),
                    dbDetails.getPassword());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return conn;
    }
}