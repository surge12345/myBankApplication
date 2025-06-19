package com.bankapp.main;
import java.util.Scanner;

import com.bankapp.models.Account;
import com.bankapp.models.Bank;
import com.bankapp.models.CurrentAccount;
import com.bankapp.models.Customer;
import com.bankapp.models.LoanAccount;
import com.bankapp.models.SavingsAccount;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank("IBANK");

        while (true) {
            System.out.println("\nWelcome to " + bank.getBankName());
            System.out.println("1. Create a new customer");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Create a new customer with login credentials
                    System.out.print("Enter customer ID: ");
                    String customerID = scanner.nextLine();
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter customer address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    
                    Customer newCustomer = new Customer(customerID, name, address, username, password);
                    bank.addCustomer(newCustomer);
                    System.out.println("Customer created successfully!");
                    break;

                case 2:
                    // Login and authentication
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    Customer customer = bank.authenticateCustomer(loginUsername, loginPassword);
                    if (customer == null) {
                        System.out.println("Invalid username or password.");
                    } else {
                        System.out.println("Login successful!");

                        // After login, show banking options
                        System.out.println("Welcome to IACCOUNT");
                        while (true) {
                           
                            System.out.println("\n1. Open an account");
                            System.out.println("2. Deposit money into an account");
                            System.out.println("3. Withdraw money from an account");
                            System.out.println("4. Transfer money between accounts");
                            System.out.println("5. Check account balance");
                            System.out.println("6. View transaction history");
                            System.out.println("7. Calculate interest");
                            System.out.println("8. Repay loan");
                            System.out.println("9. Logout");
                            System.out.print("Enter your choice: ");
                            
                            int userChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (userChoice) {
                                case 1:
                                    // Open an account
                                    System.out.print("Enter account type (Savings/Current/Loan): ");
                                    String accountType = scanner.nextLine();
                                    System.out.print("Enter initial deposit: ");
                                    double initialDeposit = scanner.nextDouble();
                                    Account newAccount = null;
                                    switch (accountType.toLowerCase()) {
                                        case "savings":
                                            newAccount = new SavingsAccount(bank.generateAccountNumber(), customer, initialDeposit, 0.04);
                                            break;
                                        case "current":
                                            System.out.print("Enter overdraft limit: ");
                                            double overdraftLimit = scanner.nextDouble();
                                            newAccount = new CurrentAccount(bank.generateAccountNumber(), customer, initialDeposit, overdraftLimit);
                                            break;
                                        case "loan":
                                            newAccount = new LoanAccount(bank.generateAccountNumber(), customer, initialDeposit, 0.07);
                                            break;
                                        default:
                                            System.out.println("Invalid account type.");
                                            break;
                                    }
                                    if (newAccount != null) {
                                        customer.openAccount(newAccount);
                                        bank.openAccount(customer, newAccount);
                                        System.out.println("Account created successfully!");
                                    }
                                    break;

                                case 2:
                                    // Deposit money
                                    System.out.print("Enter account number: ");
                                    String accountNumber = scanner.nextLine();
                                    Account account = bank.findAccountByNumber(accountNumber);
                                    if (account == null) {
                                        System.out.println("Account not found!");
                                        break;
                                    }
                                    System.out.print("Enter amount to deposit: ");
                                    double depositAmount = scanner.nextDouble();
                                    account.deposit(depositAmount);
                                    break;

                                case 3:
                                    // Withdraw money
                                    System.out.print("Enter account number: ");
                                    accountNumber = scanner.nextLine();
                                    account = bank.findAccountByNumber(accountNumber);
                                    if (account == null) {
                                        System.out.println("Account not found!");
                                        break;
                                    }
                                    System.out.print("Enter amount to withdraw: ");
                                    double withdrawAmount = scanner.nextDouble();
                                    account.withdraw(withdrawAmount);
                                    break;

                                case 4:
                                    // Transfer money
                                    System.out.print("Enter your account number: ");
                                    String fromAccountNumber = scanner.nextLine();
                                    System.out.print("Enter recipient account number: ");
                                    String toAccountNumber = scanner.nextLine();
                                    System.out.print("Enter transfer amount: ");
                                    double transferAmount = scanner.nextDouble();
                                    bank.transferFunds(fromAccountNumber, toAccountNumber, transferAmount);
                                    break;

                                case 5:
                                    // Check account balance
                                    System.out.print("Enter account number: ");
                                    accountNumber = scanner.nextLine();
                                    account = bank.findAccountByNumber(accountNumber);
                                    if (account == null) {
                                        System.out.println("Account not found!");
                                    } else {
                                        System.out.println("Current balance: $" + account.getBalance());
                                    }
                                    break;

                                case 6:
                                    // View transaction history
                                    System.out.print("Enter account number: ");
                                    accountNumber = scanner.nextLine();
                                    account = bank.findAccountByNumber(accountNumber);
                                    if (account != null) {
                                        account.printTransactionHistory();
                                    } else {
                                        System.out.println("Account not found!");
                                    }
                                    break;

                                case 7:
                                    // Calculate interest
                                    bank.calculateMonthlyInterest();
                                    break;

                                case 8:
                                    // Repay loan
                                    System.out.print("Enter loan account number: ");
                                    accountNumber = scanner.nextLine();
                                    account = bank.findAccountByNumber(accountNumber);
                                    if (account instanceof LoanAccount) {
                                        System.out.print("Enter repayment amount: ");
                                        double repaymentAmount = scanner.nextDouble();
                                        ((LoanAccount) account).repayLoan(repaymentAmount);
                                    } else {
                                        System.out.println("Invalid account number for loan repayment.");
                                    }
                                    break;

                                case 9:
                                    System.out.println("Logged out.");
                                    break;
                            }

                            if (userChoice == 9) {
                                break; // Exit the banking menu after logout
                            }
                        }
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
