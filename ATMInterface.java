import java.util.ArrayList;
import java.util.List;

public class ATMInterface {
    private Account account;
    private List<Transaction> history;

    public ATMInterface(Account account) {
        this.account = account;
        this.history = new ArrayList<>();
    }

    public void deposit(double amount) {
        account.deposit(amount);
        history.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (account.withdraw(amount)) {
            history.add(new Transaction("Withdraw", amount));
            return true;
        }
        return false;
    }

    public boolean transfer(Account receiver, double amount) {
        if (account.transfer(receiver, amount)) {
            history.add(new Transaction("Transfer to " + receiver.getUserId(), amount));
            return true;
        }
        return false;
    }

    public void printTransactionHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction t : history) {
                System.out.println(t);
            }
        }
    }

    public double getBalance() {
        return account.getBalance();
    }
}
