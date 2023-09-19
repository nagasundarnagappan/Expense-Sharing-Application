package Methods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Queries.UserQueries;
import Queries.ExpenseQueries;

import Database.Connect;

public class User {
    public static Connection conn = new Connect().getConnection();

    public static void displayAllExpensesOfSpecificUser(int expenseId) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ExpenseQueries.getExpenseById(expenseId));

        System.out.println("All the expenses in your SplitX account");
        System.out.println("Expense ID\tExpense Name\t\tExpense Amount\t\tExpense Date");

        while (rs.next()) {
            System.out.println(rs.getInt("expense_id") + "\t\t" + rs.getString("expense_name") + "\t\t"
                    + rs.getInt("expense_amount") + "\t\t" + rs.getString("expense_date") + "\t\t"
                    + rs.getInt("user_id"));
        }
    }

    public static void displaySingleExpense(int expenseId) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ExpenseQueries.getExpenseById(expenseId));

        System.out.println("-------------------------------------------------------");
        System.out.println("Expense Id : " + rs.getInt("expense_id"));
        System.out.println("Expense Name : " + rs.getString("expense_name"));
        System.out.println("Expense Description : " + rs.getString("expense_description"));
        System.out.println("Expense Amount : " + rs.getInt("expense_amount"));
        System.out.println("Expense Date : " + rs.getString("expense_date"));
        System.out.println("Shared between : ");

        int members[] = (int[]) rs.getArray("sharedUsers").getArray();

        for (int member : members) {
            System.out.println(stmt.executeQuery(UserQueries.getUserNameWithId(member)));
        }

        System.out.println("-------------------------------------------------------");
    }

    public static void 
}
