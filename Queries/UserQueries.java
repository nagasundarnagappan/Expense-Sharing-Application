package Queries;

import Database.Connect;
import java.sql.Connection;

public class UserQueries {

    Connection conn = new Connect().getConnection();

    public static String createTableIfNotExists() {
        return "CREATE TABLE IF NOT EXISTS users (user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, user_name VARCHAR(255) NOT NULL, user_password VARCHAR(255) NOT NULL)";
    }

    public static String getAllUsers() {
        return "SELECT * FROM users";
    }

    public static String getUserById(int userId) {
        return "SELECT * FROM users WHERE user_id = " + userId;
    }

    public static String getUserByName(String userName) {
        return "SELECT * FROM users WHERE user_name = '" + userName + "'";
    }

    public static String getPassword(String userName) {
        return "SELECT user_password FROM users WHERE user_name = '" + userName + "'";
    }

    public static String insertUser(String userName, String userPassword) {
        return "INSERT INTO users (user_name, user_password) VALUES ('" + userName + "', '" + userPassword + "')";
    }

    public static String updateUser(int userId, String userName, String userPassword) {
        return "UPDATE users SET user_name = '" + userName + "', user_password = '" + userPassword
                + "' WHERE user_id = " + userId;
    }

    public static String deleteUser(int userId) {
        return "DELETE FROM users WHERE user_id = " + userId;
    }

    public static String getUserNameWithId(int userId) {
        return "SELECT user_name FROM users WHERE user_id = " + userId;
    }

    public static String getUserIdWithName(String userName) {
        return "SELECT user_id FROM users WHERE user_name = '" + userName + "'";
    }
}
