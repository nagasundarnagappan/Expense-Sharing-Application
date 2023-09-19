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

    public static String getExpenseByUserId(int userId) {
        return "SELECT * FROM expenses WHERE created_by = " + userId;
    }

    public static String getExpenseByName(String expenseName) {
        return "SELECT * FROM expenses WHERE expense_name = '" + expenseName + "'";
    }

    public static String addExpense() {
        return "INSERT INTO expenses (expense_name, expense_description, expense_amount, expense_date, created_by, shared_users) VALUES (?, ?, ?, ?, ?)";
    }

    public static String updateExpense(int expenseId, String expenseName, int expenseAmount, String expenseDate,
            int userId) {
        return "UPDATE expenses SET expense_name = '" + expenseName + "', expense_amount = '" + expenseAmount
                + "', expense_date = '" + expenseDate + "', user_id = '" + userId + "' WHERE expense_id = " + expenseId;
    }

    public static String updateRemaining() {
        return "UPDATE expenses SET shared_users = ? WHERE expense_id = ?";
    }

    public static String deleteExpense(int expenseId) {
        return "DELETE FROM expenses WHERE expense_id = " + expenseId;
    }
}
