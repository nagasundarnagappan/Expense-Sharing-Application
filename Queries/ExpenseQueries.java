package Queries;

import java.sql.Connection;

import Database.Connect;

public class ExpenseQueries {
    Connection conn = new Connect().getConnection();

    public static String createTableIfNotExists() {
        return "CREATE TABLE IF NOT EXISTS expenses (expense_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, expense_name VARCHAR(255) NOT NULL, expense_description VARCHAR(255) NOT NULL, expense_amount INT NOT NULL, expense_date DATE NOT NULL, created_by INT NOT NULL)";
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
        return "INSERT INTO expenses (expense_name, expense_description, expense_amount, expense_date, created_by) VALUES (?, ?, ?, ?, ?)";
    }

    public static String deleteExpense(int expenseId) {
        return "DELETE FROM expenses WHERE expense_id = " + expenseId;
    }
}
