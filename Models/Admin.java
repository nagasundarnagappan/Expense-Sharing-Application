package Models;

public class Admin extends User {
    public Admin() {
        super();
        this.setUserId(-1);
        this.setUserName("Admin");
        this.setUserPassword("Admin");
    }
}
