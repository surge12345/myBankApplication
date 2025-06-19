package com.bankapp.models;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerID;
    private String name;
    private String address;
    private String username;
    private String password;
    private List<Account> accounts;

    public Customer(String customerID, String name, String address, String username, String password) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username; // Added getter for username
    }

    public String getPassword() {
        return password; // Added getter for password
    }

    public void openAccount(Account a) {
        accounts.add(a);
    }

    public Account getAccount(String accountNumber) {
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accountNumber)) {
                return a;
            }
        }
        return null;
    }
}
