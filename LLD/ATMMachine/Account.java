package ATMMachine;

public class Account {
    private User user;
    private float balance;
    private String number;
    
    public Account(User user, float balance, String number) {
        this.user = user;
        this.balance = balance;
        this.number = number;

    }
    
    public User getUser() {
        return user;
    }
    
    public float getBalance() {
        return balance;
    }
    
    public String getNumber() {
        return number;
    }
    
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public void setBalance(float balance) {
        this.balance = balance;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
}
