package Queries;

import java.sql.Connection;

import Database.Connect;

public class ExpenseQueries {
    Connection conn = new Connect().getConnection();

    public static String createTableIfNotExists() {
        return "CREATE TABLE IF NOT EXISTS expenses (expense_id INT NOT NULL AUTO_INCREMENT, expense_name VARCHAR(255) NOT NULL, expense_amount INT NOT NULL, expense_date DATE NOT NULL, user_id INT NOT NULL, PRIMARY KEY (expense_id), FOREIGN KEY (user_id) REFERENCES users(user_id))";
    }

    public static String getAllExpenses() {
        return "SELECT * FROM expenses";
    }

    public static String getExpenseById(int expenseId) {
        return "SELECT * FROM expenses WHERE expense_id = " + expenseId;
    }

    public static String getExpenseByName(String expenseName) {
        return "SELECT * FROM expenses WHERE expense_name = '" + expenseName + "'";
    }

    public static String insertExpense(String expenseName, int expenseAmount, String expenseDate, int userId) {
        return "INSERT INTO expenses (expense_name, expense_amount, expense_date, user_id) VALUES ('" + expenseName
                + "', '" + expenseAmount + "', '" + expenseDate + "', '" + userId + "')";
    }

    public static String updateExpense(int expenseId, String expenseName, int expenseAmount, String expenseDate,
            int userId) {
        return "UPDATE expenses SET expense_name = '" + expenseName + "', expense_amount = '" + expenseAmount
                + "', expense_date = '" + expenseDate + "', user_id = '" + userId + "' WHERE expense_id = " + expenseId;
    }

    public static String deleteExpense(int expenseId) {
        return "DELETE FROM expenses WHERE expense_id = " + expenseId;
    }
}
