package Methods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Queries.UserQueries;
import Queries.ExpenseQueries;

import Database.Connect;

public class Admin implements CommonMethods {
    public static Connection conn = new Connect().getConnection();

    public static void viewUser() throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(UserQueries.getAllUsers());

        System.out.println("All the users in SplitX");
        System.out.println("User ID\tUser Name\t\tUser Password");

        while (rs.next()) {
            System.out.println(rs.getInt("user_id") + "\t" + rs.getString("user_name") + "\t\t"
                    + rs.getString("user_password"));
        }
    }

    public static void deleteUser(int userId) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute(UserQueries.deleteUser(userId));
        System.out.println("User deleted successfully!");
    }

    public static void displayAllExpenses() throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ExpenseQueries.getAllExpenses());

        System.out.println("All the expenses in SplitX");
        System.out.println("Expense ID\tExpense Name\t\tExpense Amount\t\tExpense Date\t\tUser ID");

        while (rs.next()) {
            System.out.println(rs.getInt("expense_id") + "\t\t" + rs.getString("expense_name") + "\t\t"
                    + rs.getInt("expense_amount") + "\t\t" + rs.getString("expense_date") + "\t\t"
                    + rs.getInt("created_by"));
        }
    }

    public void deleteExpense(int expenseId) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute(ExpenseQueries.deleteExpense(expenseId));
        System.out.println("Expense deleted successfully!");
    }

    public void displaySingleExpense(int expenseId) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ExpenseQueries.getExpenseById(expenseId));
        rs.next();

        System.out.println("-------------------------------------------------------");
        System.out.println("Expense Id : " + rs.getInt("expense_id"));
        System.out.println("Expense Name : " + rs.getString("expense_name"));
        System.out.println("Expense Description : " + rs.getString("expense_description"));
        System.out.println("Expense Amount : " + rs.getInt("expense_amount"));
        System.out.println("Expense Date : " + rs.getString("expense_date"));
        System.out.println("Created by User Id : " + rs.getInt("created_by"));
        System.out.println("-------------------------------------------------------");
    }

    public static void viewUser(int userId) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(UserQueries.getUserById(userId));
        rs.next();

        System.out.println("-------------------------------------------------------");
        System.out.println("User Id : " + rs.getInt("user_id"));
        System.out.println("User Name : " + rs.getString("user_name"));
        System.out.println("User Password : " + rs.getString("user_password"));
        System.out.println("-------------------------------------------------------");
    }

}
