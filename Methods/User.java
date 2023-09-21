package Methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;

import Queries.UserQueries;
import Queries.ExpenseQueries;
import Queries.SharesQueries;

import Database.Connect;

public class User implements CommonMethods {
    public static Connection conn = new Connect().getConnection();

    public static void displayAllExpensesOfSpecificUser(int userId) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ExpenseQueries.getExpenseByUserId(userId));

        System.out.println("All the expenses in your SplitX account");
        System.out.println("Expense ID\tExpense Name\t\tExpense Amount\t\tExpense Date");

        while (rs.next()) {
            System.out.println(rs.getInt("expense_id") + "\t\t" + rs.getString("expense_name") + "\t\t"
                    + rs.getInt("expense_amount") + "\t\t" + rs.getString("expense_date"));
        }
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
        System.out.println("Shared between : ");

        PreparedStatement pstmt = conn.prepareStatement(SharesQueries.getSharesByExpenseId(expenseId));
        ResultSet rs2 = pstmt.executeQuery();

        while (rs2.next()) {
            ResultSet rss = stmt.executeQuery(UserQueries.getUserNameWithId(rs2.getInt("user_id")));
            rss.next();
            System.out.println(rss.getString("user_name"));
        }

        System.out.println("-------------------------------------------------------");
    }

    public static void addExpense(int userId, String expenseName, String expenseDescription, double expenseAmount,
            String expenseDate, String[] sharedUsersNames) throws Exception {
        Statement stmt = conn.createStatement();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date parsedDate = dateFormat.parse(expenseDate);
        Date sqlDate = new Date(parsedDate.getTime());

        int numberOfUsers = sharedUsersNames.length;

        int sharedUsers[] = new int[numberOfUsers];

        for (int i = 0; i < numberOfUsers; i++) {
            ResultSet rs2 = stmt.executeQuery(UserQueries.getUserIdWithName(sharedUsersNames[i]));
            rs2.next();

            sharedUsers[i] = rs2.getInt("user_id");
        }

        PreparedStatement pstmt = conn.prepareStatement(ExpenseQueries.addExpense());

        pstmt.setString(1, expenseName);
        pstmt.setString(2, expenseDescription);
        pstmt.setDouble(3, expenseAmount);
        pstmt.setDate(4, sqlDate);
        pstmt.setInt(5, userId);

        pstmt.executeUpdate();

        ResultSet rss = stmt.executeQuery(ExpenseQueries.getExpenseByName(expenseName));
        rss.next();

        int expenseId = rss.getInt("expense_id");

        pstmt = conn.prepareStatement(SharesQueries.insertShares());

        for (int i = 0; i < numberOfUsers; i++) {
            pstmt.setInt(1, expenseId);
            pstmt.setInt(2, sharedUsers[i]);

            pstmt.executeUpdate();
        }

        System.out.println("Expense added successfully");
    }

    public void deleteExpense(int expenseId) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(ExpenseQueries.deleteExpense(expenseId));

        Statement stmt2 = conn.createStatement();
        stmt2.executeUpdate(SharesQueries.deleteSharesByExpenseId(expenseId));

        System.out.println("Expense deleted successfully");

    }

    public static void payExpense(int userId, int expenseId) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute(SharesQueries.deleteSharesByUserIdAndExpenseId(userId, expenseId));

        System.out.println("Expense paid successfully");
    }

    public static void markReceived(int expenseId, String userName) throws Exception {
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(UserQueries.getUserIdWithName(userName));
        rs.next();

        int userId = rs.getInt("user_id");

        stmt.execute(SharesQueries.deleteSharesByUserIdAndExpenseId(userId, expenseId));

        System.out.println("Expense marked as received successfully");
    }

    public static void displayExpensesToReceive(int userId) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ExpenseQueries.getAllExpenses());

        System.out.println("All the expenses to be paid to you");
        System.out.println("Expense ID\tExpense Name\t\tExpense Amount\t\tExpense Date");

        ArrayList<Integer> expIds = new ArrayList<Integer>();
        ArrayList<Integer> wantExpIds = new ArrayList<Integer>();

        while (rs.next()) {
            expIds.add(rs.getInt("expense_id"));
        }

        for (int id : expIds) {
            ResultSet rss = stmt.executeQuery(SharesQueries.numberOfSharesByExpenseId(id));
            rss.next();

            int count = (rss.getInt("COUNT(*)"));

            if (count > 0) {
                wantExpIds.add(id);
            }
        }

        for (int id : wantExpIds) {
            ResultSet rss = stmt.executeQuery(ExpenseQueries.getExpenseById(id));
            rss.next();

            System.out.println(rss.getInt("expense_id") + "\t\t" + rss.getString("expense_name") + "\t\t"
                    + rss.getInt("expense_amount") + "\t\t" + rss.getString("expense_date"));
        }
    }

    public static void displayAllExpenseToPay(int userId) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SharesQueries.getSharesByUserId(userId));

        System.out.println("All the expenses to be paid by you");
        System.out.println("Expense ID\tExpense Name\t\tExpense Amount\t\tExpense Date");

        ArrayList<Integer> expIds = new ArrayList<Integer>();

        while (rs.next()) {
            expIds.add(rs.getInt("expense_id"));
        }

        for (int id : expIds) {
            ResultSet rss = stmt.executeQuery(ExpenseQueries.getExpenseById(id));
            rss.next();

            System.out.println(rss.getInt("expense_id") + "\t\t" + rss.getString("expense_name") + "\t\t"
                    + rss.getInt("expense_amount") + "\t\t" + rss.getString("expense_date"));
        }
    }
}
