public class User {
    private String username;
    private String password;
    private double balance;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 00.00; // Initial balance
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
