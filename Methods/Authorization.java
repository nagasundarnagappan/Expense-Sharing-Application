package Methods;

import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.Connect;
import Queries.UserQueries;

public class Authorization {

    public static Connection conn = new Connect().getConnection();

    public static boolean checkIfUsernameIsUnique(String userName) throws Exception {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(UserQueries.getUserByName(userName)) == null;
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
        return stmt.executeQuery(UserQueries.getUserByName(userName)).getString("user_password")
                .equals(userPassword);
    }
}