package Models;

public class Expense {
    private int expenseId;

    private String expenseName;

    private String expenseDescription;

    private double expenseAmount;

    private String expenseDate;

    private int createdBy;

    public Expense() {
    }

    public Expense(int expenseId, String expenseName, String expenseDescription, double expenseAmount,
            String expenseDate, int createdBy) {
        this.expenseId = expenseId;
        this.expenseName = expenseName;
        this.expenseDescription = expenseDescription;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
        this.createdBy = createdBy;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
}
