package org.example;
import java.util.Scanner;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false; // Insufficient funds
        }
        balance -= amount;
        return true; // Withdrawal successful
    }
}

// ATM class represents the ATM
class ATM {
    private BankAccount bankAccount;
    private Scanner scanner;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nWelcome to the ATM");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
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
                    System.out.println("Thank you for using the ATM!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void checkBalance() {
        double balance = bankAccount.getBalance();
        System.out.printf("Your current balance is: $%.2f%n", balance);
    }

    private void deposit() {
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        bankAccount.deposit(amount);
        System.out.printf("Deposit of $%.2f successful.%n", amount);
        checkBalance();
    }

    private void withdraw() {
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        boolean success = bankAccount.withdraw(amount);
        if (success) {
            System.out.printf("Withdrawal of $%.2f successful.%n", amount);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
        checkBalance();
    }

    public static void main(String[] args) {
        // Create a bank account with initial balance of $1000
        BankAccount bankAccount = new BankAccount(1000.0);

        // Create an ATM instance using the bank account
        ATM atm = new ATM(bankAccount);

        // Start the ATM interface
        atm.start();
    }
}