package com.bankapp.models;

public class CurrentAccount extends Account {
    private double overdraftLimit = 100.0;
    public CurrentAccount(String accountNumber, Customer accountHolder, double initialDeposit, double overdraftLimit) {
        super(accountNumber, accountHolder, initialDeposit);
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative.");
        }
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
        if (amount > (balance + overdraftLimit)) {
            System.out.println("Overdraft limit exceeded.");
            return false;
        }
        balance -= amount;
        logTransaction("Withdrawal", amount);
        System.out.println("Withdrawal successful. New balance: $" + balance);
        return true;
    }

    @Override
    public void applyInterest() {
        // No interest applied for current accounts.
    }
}

