package com.bankapp.models;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankName;
    private List<Customer> customers;
    private List<Account> accounts;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public String getBankName() {
        return bankName;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        saveCustomerToCSV(customer);
    }

    public void saveCustomerToCSV(Customer customer) {
        try (FileWriter writer = new FileWriter("customers.csv", true)) {
            writer.append(customer.getCustomerID()).append(',')
                  .append(customer.getName()).append(',')
                  .append(customer.getAddress()).append(',')
                  .append(customer.getUsername()).append(',')
                  .append(customer.getPassword()).append('\n');
        } catch (IOException e) {
            System.out.println("Error saving customer to CSV: " + e.getMessage());
        }
    }

    public void openAccount(Customer customer, Account account) {
        accounts.add(account);
        customer.openAccount(account);
        saveAccountToCSV(account);
    }

    public void saveAccountToCSV(Account account) {
        try (FileWriter writer = new FileWriter("accounts.csv", true)) {
            writer.append(account.getAccountNumber()).append(',')
                  .append(account.getAccountHolder().getCustomerID()).append(',')
                  .append(String.valueOf(account.getBalance())).append(',')
                  .append(account.getClass().getSimpleName()).append('\n');
        } catch (IOException e) {
            System.out.println("Error saving account to CSV: " + e.getMessage());
        }
    }

    public Account findAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findAccountByNumber(fromAccountNumber);
        Account toAccount = findAccountByNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            System.out.println("Invalid account number(s) for transfer.");
            return;
        }

        if (fromAccount.withdraw(amount)) {
            toAccount.deposit(amount);
            System.out.println("Transfer successful.");
            saveTransactionToCSV("Transfer", fromAccount.getAccountNumber(), amount);
        }
    }

    public void saveTransactionToCSV(String transactionType, String accountNumber, double amount) {
        try (FileWriter writer = new FileWriter("transactions.csv", true)) {
            writer.append(transactionType).append(',')
                  .append(accountNumber).append(',')
                  .append(String.valueOf(amount)).append('\n');
        } catch (IOException e) {
            System.out.println("Error saving transaction to CSV: " + e.getMessage());
        }
    }

    public void calculateMonthlyInterest() {
        for (Account account : accounts) {
            account.applyInterest();
        }
    }

    public String generateAccountNumber() {
        return "ACCT" + (accounts.size() + 1); // Simple account number generation
    }

    // Method to authenticate the customer
    public Customer authenticateCustomer(String username, String password) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                return customer; // Return the authenticated customer
            }
        }
        return null; // Return null if authentication fails
    }
}
