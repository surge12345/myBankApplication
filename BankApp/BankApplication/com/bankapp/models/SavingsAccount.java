package com.bankapp.models;

public class SavingsAccount extends Account {
    private double annualInterestRate; // Annual interest rate as a decimal (e.g., 0.04 for 4%)

    public SavingsAccount(String accountNumber, Customer accountHolder, double initialDeposit, double annualInterestRate) {
        super(accountNumber, accountHolder, initialDeposit);
        if (annualInterestRate < 0) {
            throw new IllegalArgumentException("Annual interest rate cannot be negative.");
        }
        this.annualInterestRate = annualInterestRate;
    }

    @Override
    public void applyInterest() {
        // Calculate monthly interest
        double monthlyInterest = balance * (annualInterestRate / 12);
        balance += monthlyInterest; // Apply interest to balance
        logTransaction("Monthly Interest", monthlyInterest); // Log the interest transaction
        System.out.println("Monthly interest of $" + monthlyInterest + " applied. New balance: $" + balance);
    }
}
