package Methods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.Connect;
import Queries.UserQueries;

public class Authorization {

    public static Connection conn = new Connect().getConnection();

    public static boolean checkIfUsernameIsUnique(String userName) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(UserQueries.getUserByName(userName));

        if (rs.next()) {
            System.out.println("Username already exists!" + rs.getString("user_name"));
            return false;
        }

        return true;
    }

    public static boolean checkPasswordStrength(String userPassword) {
        if (userPassword.length() < 8) {
            return false;
        }

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userPassword);

        if (!matcher.matches()) {
            return false;
        }

        return true;
    }

    public static boolean signUp(String userName, String userPassword) throws Exception {
        Statement stmt = conn.createStatement();
        return stmt.execute(UserQueries.insertUser(userName, userPassword));
    }

    public static boolean signIn(String userName, String userPassword) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(UserQueries.getUserByName(userName));

        System.out.println("User Name : " + userName);
        System.out.println("User Password : " + userPassword);

        while (rs.next()) {
            System.out.println("User Name : " + rs.getString("user_name"));
            String pw = rs.getString("user_password");
            System.out.println("User Password : " + pw);
            if (pw.equals(userPassword)) {
                System.out.println("User signed in successfully!");
                return true;
            }
        }

        return false;
    }

    public static int getUserId(String userName) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(UserQueries.getUserByName(userName));
        rs.next();

        return rs.getInt("user_id");
    }
}