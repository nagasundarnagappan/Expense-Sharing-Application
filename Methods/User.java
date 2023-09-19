package Methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Scanner;

import Queries.UserQueries;
import Queries.ExpenseQueries;

import Database.Connect;

public class User {
    public static Connection conn = new Connect().getConnection();

    public static void displayAllExpensesOfSpecificUser(int userId) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ExpenseQueries.getExpenseByUserId(userId));

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

    public static void addExpense(int userId) throws Exception {
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the expense name : ");
        String expenseName = sc.nextLine();

        System.out.println("Enter the expense description : ");
        String expenseDescription = sc.nextLine();

        System.out.println("Enter the expense amount : ");
        double expenseAmount = sc.nextDouble();

        System.out.println("Enter the expense date : ");
        String expenseDate = sc.nextLine();

        System.out.println("Enter the number of users to share the expense with : ");
        int numberOfUsers = sc.nextInt();

        String sharedUsersNames[] = new String[numberOfUsers];

        for (int i = 0; i < numberOfUsers; i++) {
            System.out.println("Enter the user name of the user to share the expense with : ");
            sharedUsersNames[i] = sc.nextLine();
        }

        Integer[] sharedUsers = new Integer[numberOfUsers];

        for (int i = 0; i < numberOfUsers; i++) {
            ResultSet rs = stmt.executeQuery(UserQueries.getUserIdWithName(sharedUsersNames[i]));
            sharedUsers[i] = rs.getInt("user_id");
        }

        PreparedStatement pstmt = conn.prepareStatement(ExpenseQueries.addExpense());

        pstmt.setString(1, expenseName);
        pstmt.setString(2, expenseDescription);
        pstmt.setDouble(3, expenseAmount);
        pstmt.setString(4, expenseDate);
        pstmt.setInt(5, userId);
        pstmt.setArray(6, conn.createArrayOf("INTEGER", sharedUsers));

        pstmt.executeUpdate();

        System.out.println("Expense added successfully");
        sc.close();
    }

    public static void deleteExpense(int expenseId) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(ExpenseQueries.deleteExpense(expenseId));

        System.out.println("Expense deleted successfully");
    }

    public static void payExpense(int userId, int expenseId) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ExpenseQueries.getExpenseById(expenseId));

        int members[] = (int[]) rs.getArray("sharedUsers").getArray();

        Integer newMembers[] = new Integer[members.length - 1];
        int j = 0;
        for (int i = 0; i < members.length; i++) {
            if (members[i] != userId) {
                newMembers[j] = members[i];
                j++;
            }
        }

        PreparedStatement pstmt = conn.prepareStatement(ExpenseQueries.updateRemaining());

        pstmt.setInt(2, expenseId);
        pstmt.setArray(1, conn.createArrayOf("INTEGER", newMembers));

        System.out.println("Expense paid successfully");
    }

    public static void markReceived(int expenseId, String userName) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ExpenseQueries.getExpenseById(expenseId));

        int members[] = (int[]) rs.getArray("sharedUsers").getArray();

        int userId = stmt.executeQuery(UserQueries.getUserByName(userName)).getInt("user_id");

        Integer newMembers[] = new Integer[members.length - 1];
        int j = 0;
        for (int i = 0; i < members.length; i++) {
            if (members[i] != userId) {
                newMembers[j] = members[i];
                j++;
            }
        }

        PreparedStatement pstmt = conn.prepareStatement(ExpenseQueries.updateRemaining());

        pstmt.setInt(2, expenseId);
        pstmt.setArray(1, conn.createArrayOf("INTEGER", newMembers));

        System.out.println("Expense marked as received successfully");
    }

    public static void displayExpensesToReceive(int userId) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ExpenseQueries.getExpenseByUserId(userId));

        System.out.println("All the expenses to be paid to you");
        System.out.println("Expense ID\tExpense Name\t\tExpense Amount\t\tExpense Date");

        while (rs.next()) {
            int members[] = (int[]) rs.getArray("sharedUsers").getArray();

            if (members.length > 0)
                System.out.println(rs.getInt("expense_id") + "\t\t" + rs.getString("expense_name") + "\t\t"
                        + rs.getInt("expense_amount") + "\t\t" + rs.getString("expense_date") + "\t\t"
                        + rs.getInt("user_id"));
        }
    }

    public static void displayAllExpenseToPay(int userId) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(ExpenseQueries.getAllExpenses());

        System.out.println("All the expenses to be paid by you");
        System.out.println("Expense ID\tExpense Name\t\tExpense Amount\t\tExpense Date");

        while (rs.next()) {
            int members[] = (int[]) rs.getArray("sharedUsers").getArray();

            for (int member : members) {
                if (member == userId) {
                    System.out.println(rs.getInt("expense_id") + "\t\t" + rs.getString("expense_name") + "\t\t"
                            + rs.getInt("expense_amount") + "\t\t" + rs.getString("expense_date") + "\t\t"
                            + rs.getInt("user_id"));
                    break;
                }
            }
        }
    }
}
