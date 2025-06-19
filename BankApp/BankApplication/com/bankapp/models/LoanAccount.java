package com.bankapp.models;

public class LoanAccount extends Account {
    private double loanAmount; // The original loan amount
    private double interestRate;

    public LoanAccount(String accountNumber, Customer accountHolder, double loanAmount, double interestRate) {
        super(accountNumber, accountHolder, 0); // Initialize balance to 0, as it's not used as loan amount.
        if (loanAmount < 0) {
            throw new IllegalArgumentException("Loan amount cannot be negative.");
        }
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.balance = loanAmount; // Start with the loan amount as the balance owed.
    }

    @Override
    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        logTransaction("Loan Interest", interest);
    }

    public void repayLoan(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            logTransaction("Loan Repayment", amount);
            System.out.println("Loan repayment successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid repayment amount.");
        }
    }

    public double getOutstandingLoan() {
        return balance; // Outstanding loan balance
    }

    public double getLoanAmount() {
        return loanAmount; // The original loan amount
    }
}
