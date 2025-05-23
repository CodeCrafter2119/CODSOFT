import java.util.Scanner;

public class ATMSimulation {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}

class ATM {
    private BankAccount userAccount;
    private Scanner scanner;

    public ATM() {
        // Initialize with a bank account containing $1000 balance
        userAccount = new BankAccount(1000);
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM Machine!");
        
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number (1-4): ");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

    private void checkBalance() {
        System.out.printf("\nYour current balance is: $%.2f\n", userAccount.getBalance());
    }

    private void deposit() {
        System.out.print("\nEnter amount to deposit: $");
        double amount = getValidAmount();

        if (amount > 0) {
            userAccount.deposit(amount);
            System.out.printf("$%.2f has been deposited successfully.\n", amount);
            System.out.printf("New balance: $%.2f\n", userAccount.getBalance());
        } else {
            System.out.println("Invalid amount. Deposit amount must be positive.");
        }
    }

    private void withdraw() {
        System.out.print("\nEnter amount to withdraw: $");
        double amount = getValidAmount();

        if (amount <= 0) {
            System.out.println("Invalid amount. Withdrawal amount must be positive.");
            return;
        }

        if (userAccount.withdraw(amount)) {
            System.out.printf("$%.2f has been withdrawn successfully.\n", amount);
            System.out.printf("Remaining balance: $%.2f\n", userAccount.getBalance());
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    private double getValidAmount() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a numeric value: $");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextDouble();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
