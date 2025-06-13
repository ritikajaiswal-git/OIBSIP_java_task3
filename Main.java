import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Sample user accounts
        HashMap<String, Account> userAccounts = new HashMap<>();
        userAccounts.put("user1", new Account("user1", "1234"));
        userAccounts.put("user2", new Account("user2", "4321")); // You can add more

        Scanner scanner = new Scanner(System.in);

        System.out.println("==== Welcome to the ATM ====");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        // Validate login
        if (!userAccounts.containsKey(userId) || !userAccounts.get(userId).authenticate(pin)) {
            System.out.println("❌ Invalid credentials! Exiting.");
            return;
        }

        Account currentUser = userAccounts.get(userId);
        ATMInterface atm = new ATMInterface(currentUser);

        while (true) {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. View Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Quit");

            System.out.print("Choose an option (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    atm.printTransactionHistory();
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (atm.withdraw(withdrawAmount)) {
                        System.out.println("✅ Withdrawal successful.");
                    } else {
                        System.out.println("❌ Insufficient balance.");
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    System.out.println("✅ Deposit successful.");
                    break;

                case 4:
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Enter receiver's User ID: ");
                    String receiverId = scanner.nextLine();
                    if (!userAccounts.containsKey(receiverId)) {
                        System.out.println("❌ Receiver not found.");
                        break;
                    }
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    Account receiver = userAccounts.get(receiverId);
                    if (atm.transfer(receiver, transferAmount)) {
                        System.out.println("✅ Transfer successful.");
                    } else {
                        System.out.println("❌ Transfer failed. Insufficient balance.");
                    }
                    break;

                case 5:
                    System.out.println("💰 Current Balance: ₹" + atm.getBalance());
                    break;

                case 6:
                    System.out.println("👋 Thank you for using the ATM. Goodbye!");
                    return;

                default:
                    System.out.println("⚠️ Invalid option. Please try again.");
            }
        }
    }
}


        
        
