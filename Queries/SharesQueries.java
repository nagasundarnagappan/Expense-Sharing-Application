package Queries;

public class SharesQueries {

    public static String createTableIfNotExists() {
        return "CREATE TABLE IF NOT EXISTS shares (share_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, expense_id INT NOT NULL, user_id INT NOT NULL)";
    }

    public static String insertShares() {
        return "INSERT INTO shares (expense_id, user_id) VALUES (?, ?)";
    }

    public static String getSharesByExpenseId(int expenseId) {
        return "SELECT * FROM shares WHERE expense_id = " + expenseId;
    }

    public static String getSharesByUserId(int userId) {
        return "SELECT * FROM shares WHERE user_id = " + userId;
    }

    public static String deleteSharesByExpenseId(int expenseId) {
        return "DELETE FROM shares WHERE expense_id = " + expenseId;
    }

    public static String deleteSharesByUserIdAndExpenseId(int userId, int expenseId) {
        return "DELETE FROM shares WHERE user_id = " + userId + " AND expense_id = " + expenseId;
    }

    public static String numberOfSharesByExpenseId(int expenseId) {
        return "SELECT COUNT(*) FROM shares WHERE expense_id = " + expenseId;
    }
}
