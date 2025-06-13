public class Account {
    private String userId;
    private String userPin;
    private double balance;

    public Account(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0.0;
    }

    public boolean authenticate(String enteredPin) {
        return userPin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(Account receiver, double amount) {
        if (withdraw(amount)) {
            receiver.deposit(amount);
            return true;
        }
        return false;
    }

    public String getUserId() {
        return userId;
    }
}

