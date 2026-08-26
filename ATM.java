import java.util.Scanner;

class BankAccount {

    private double balance;

    BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double checkBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance = balance + amount;
        return true;
    }

    public boolean withdraw(double amount) {

        if (amount <= 0) {
            return false;
        }

        if (amount > balance) {
            return false;
        }

        balance = balance - amount;
        return true;
    }
}

public class ATM {

    private BankAccount account;
    private Scanner scanner;

    ATM(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        int choice;

        do {
            System.out.println("\n========================");
            System.out.println("       ATM MACHINE");
            System.out.println("========================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.println("========================");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    checkBalance();
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM.");
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1-4.");
            }

        } while (choice != 4);
    }

    public void checkBalance() {

        double balance = account.checkBalance();

        System.out.println("Current Balance: ₹" + balance);
    }

    public void deposit(double amount) {

        if (account.deposit(amount)) {
            System.out.println("₹" + amount + " deposited successfully.");
            System.out.println("Updated Balance: ₹"
                    + account.checkBalance());
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        }
        else if (amount > account.checkBalance()) {
            System.out.println("Insufficient balance.");
        }
        else if (account.withdraw(amount)) {
            System.out.println("₹" + amount + " withdrawn successfully.");
            System.out.println("Remaining Balance: ₹"
                    + account.checkBalance());
        }
    }

    public static void main(String[] args) {

        BankAccount account = new BankAccount(10000);

        ATM atm = new ATM(account);

        atm.showMenu();
    }
}