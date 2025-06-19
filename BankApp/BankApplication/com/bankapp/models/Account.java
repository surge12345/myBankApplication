package com.bankapp.models;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected Customer accountHolder; // This holds the customer associated with the account
    protected List<Transaction> transactionLog;

    public Account(String accountNumber, Customer accountHolder, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder; // Initialize accountHolder
        this.balance = initialDeposit;
        this.transactionLog = new ArrayList<>();
        logTransaction("Account opened", initialDeposit);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getAccountHolder() { // Getter for accountHolder
        return accountHolder;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        balance += amount;
        logTransaction("Deposit", amount);
        System.out.println("Deposit of $" + amount + " successful. Current balance: $" + balance);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        }
        balance -= amount;
        logTransaction("Withdrawal", amount);
        System.out.println("Withdrawal of $" + amount + " successful. Current balance: $" + balance);
        return true;
    }

    public abstract void applyInterest();

    protected void logTransaction(String type, double amount) {
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), accountNumber, type, amount, new Date());
        transactionLog.add(transaction);
        saveTransactionToCSV(transaction); // Save transaction to CSV
    }

    public void printTransactionHistory() {
        System.out.println("Transaction history for account: " + accountNumber);
        if (transactionLog.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (Transaction t : transactionLog) {
            System.out.println(t);
        }
    }

    private void saveTransactionToCSV(Transaction transaction) {
        try (FileWriter writer = new FileWriter("transactions.csv", true)) {
            writer.append(transaction.getTransactionType()).append(',')
                  .append(transaction.getAccountNumber()).append(',')
                  .append(String.valueOf(transaction.getAmount())).append(',')
                  .append(String.valueOf(transaction.getDate().getTime())).append('\n'); // Save date as timestamp
        } catch (IOException e) {
            System.out.println("Error saving transaction to CSV: " + e.getMessage());
        }
    }
}
