package Database;

public abstract class DatabaseDetails {
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/splitx";
    String username = "root";
    private String password = "root";

    public String getPassword() {
        return password;
    }
}
