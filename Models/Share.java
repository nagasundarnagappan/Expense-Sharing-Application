package Models;

public class Share {
    private int shareId;

    private int expenseId;

    private int userId;

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Share() {
    }

    public Share(int shareId, int expenseId, int userId) {
        this.shareId = shareId;
        this.expenseId = expenseId;
        this.userId = userId;
    }

}
