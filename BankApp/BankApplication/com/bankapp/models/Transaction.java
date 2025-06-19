package com.bankapp.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String transactionID;
    private String accountNumber;
    private String transactionType;
    private double amount;
    private Date date;

    public Transaction(String transactionID, String accountNumber, String transactionType, double amount, Date date) {
        if (amount < 0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative.");
        }
        this.transactionID = transactionID;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return transactionType + " of $" + amount + " on " + dateFormat.format(date) +
                " (Transaction ID: " + transactionID + ", Account Number: " + accountNumber + ")";
    }

    // Getters
    public String getTransactionID() {
        return transactionID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
