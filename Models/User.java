package Models;

public class User {
    private int userId;

    private String userName;

    private String userPassword;

    private Expense[] expenses;

    public User() {
    }

    public User(int userId, String userName, String userPassword, Expense[] expenses) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.expenses = expenses;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Expense[] getExpenses() {
        return expenses;
    }

    public void setExpenses(Expense[] expenses) {
        this.expenses = expenses;
    }
}
