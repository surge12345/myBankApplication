package com.bankapp.gui;

import com.bankapp.models.*;
import javax.swing.*;
import java.awt.*;

public class BankAppGUI {

    private JFrame frame;
    private Bank bank;
    private Customer loggedInCustomer;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BankAppGUI window = new BankAppGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BankAppGUI() {
        initialize();
    }

    private void initialize() {
        bank = new Bank("IBANK");

        frame = new JFrame();
        frame.setTitle("IBANK Application");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(60, 179, 113)); // Green background
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel lblWelcome = new JLabel("Welcome to IBANK");
        lblWelcome.setForeground(Color.WHITE); // Set text color
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 24));
        panelTop.add(lblWelcome);

        frame.add(panelTop, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridBagLayout());
        panelCenter.setBackground(new Color(240, 255, 240)); // Light green background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(173, 216, 230)); // Light blue button
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.addActionListener(e -> showLoginDialog());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCenter.add(btnLogin, gbc);

        JButton btnCreateCustomer = new JButton("Create New Customer");
        btnCreateCustomer.setBackground(new Color(173, 216, 230)); // Light blue button
        btnCreateCustomer.setFont(new Font("Arial", Font.BOLD, 16));
        btnCreateCustomer.addActionListener(e -> showCreateCustomerDialog());
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCenter.add(btnCreateCustomer, gbc);

        frame.add(panelCenter, BorderLayout.CENTER);
    }

    private void showLoginDialog() {
        JDialog loginDialog = new JDialog(frame, "Login", true);
        loginDialog.setSize(300, 200);
        loginDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField(15);
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField(15);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(144, 238, 144)); // Light green
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            Customer customer = bank.authenticateCustomer(username, password);
            if (customer == null) {
                JOptionPane.showMessageDialog(loginDialog, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                loggedInCustomer = customer;
                JOptionPane.showMessageDialog(loginDialog, "Login successful!");
                showDashboard();
                loginDialog.dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginDialog.add(lblUsername, gbc);

        gbc.gridx = 1;
        loginDialog.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginDialog.add(lblPassword, gbc);

        gbc.gridx = 1;
        loginDialog.add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginDialog.add(btnLogin, gbc);

        loginDialog.setLocationRelativeTo(frame);
        loginDialog.setVisible(true);
    }

    private void showCreateCustomerDialog() {
        JDialog createCustomerDialog = new JDialog(frame, "Create Customer", true);
        createCustomerDialog.setSize(400, 300);
        createCustomerDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblID = new JLabel("Customer ID:");
        JTextField txtID = new JTextField(15);
        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField(15);
        JLabel lblAddress = new JLabel("Address:");
        JTextField txtAddress = new JTextField(15);
        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField(15);
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField(15);

        JButton btnSubmit = new JButton("Create");
        btnSubmit.setBackground(new Color(144, 238, 144)); // Light green
        btnSubmit.addActionListener(e -> {
            String id = txtID.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            Customer newCustomer = new Customer(id, name, address, username, password);
            bank.addCustomer(newCustomer);
            JOptionPane.showMessageDialog(createCustomerDialog, "Customer created successfully!");
            createCustomerDialog.dispose();
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        createCustomerDialog.add(lblID, gbc);

        gbc.gridx = 1;
        createCustomerDialog.add(txtID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        createCustomerDialog.add(lblName, gbc);

        gbc.gridx = 1;
        createCustomerDialog.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        createCustomerDialog.add(lblAddress, gbc);

        gbc.gridx = 1;
        createCustomerDialog.add(txtAddress, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        createCustomerDialog.add(lblUsername, gbc);

        gbc.gridx = 1;
        createCustomerDialog.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        createCustomerDialog.add(lblPassword, gbc);

        gbc.gridx = 1;
        createCustomerDialog.add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        createCustomerDialog.add(btnSubmit, gbc);

        createCustomerDialog.setLocationRelativeTo(frame);
        createCustomerDialog.setVisible(true);
    }

    private void showDashboard() {
        frame.getContentPane().removeAll(); // Clear existing components

        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(70, 130, 180)); // Steel blue background
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel lblDashboard = new JLabel("Welcome, " + loggedInCustomer.getName());
        lblDashboard.setForeground(Color.WHITE);
        lblDashboard.setFont(new Font("Arial", Font.BOLD, 22));
        panelTop.add(lblDashboard);

        frame.add(panelTop, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridBagLayout());
        panelCenter.setBackground(new Color(230, 230, 250)); // Lavender background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton btnOpenAccount = new JButton("Open Account");
        btnOpenAccount.setBackground(new Color(255, 182, 193)); // Light pink
        btnOpenAccount.setFont(new Font("Arial", Font.BOLD, 16));
        btnOpenAccount.addActionListener(e -> openAccountDialog());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCenter.add(btnOpenAccount, gbc);

        JButton btnDeposit = new JButton("Deposit Money");
        btnDeposit.addActionListener(e -> depositDialog());
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCenter.add(btnDeposit, gbc);

        JButton btnWithdraw = new JButton("Withdraw Money");
        btnWithdraw.addActionListener(e -> withdrawDialog());
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCenter.add(btnWithdraw, gbc);

        JButton btnTransfer = new JButton("Transfer Money");
        btnTransfer.addActionListener(e -> transferDialog());
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelCenter.add(btnTransfer, gbc);

        JButton btnCheckBalance = new JButton("Check Balance");
        btnCheckBalance.addActionListener(e -> checkBalanceDialog());
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelCenter.add(btnCheckBalance, gbc);

        JButton btnTransactionHistory = new JButton("View Transaction History");
        btnTransactionHistory.addActionListener(e -> transactionHistoryDialog());
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelCenter.add(btnTransactionHistory, gbc);

        JButton btnCalculateInterest = new JButton("Calculate Interest");
        btnCalculateInterest.addActionListener(e -> calculateInterest());
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelCenter.add(btnCalculateInterest, gbc);

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> logout());
        gbc.gridx = 0;
        gbc.gridy = 7;
        panelCenter.add(btnLogout, gbc);

        frame.add(panelCenter, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void openAccountDialog() {
        // Similar to the create account functionality in Main.java
        JDialog openAccountDialog = new JDialog(frame, "Open Account", true);
        openAccountDialog.setSize(400, 300);
        openAccountDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblAccountType = new JLabel("Account Type (Savings/Current/Loan):");
        JTextField txtAccountType = new JTextField(15);
        JLabel lblInitialDeposit = new JLabel("Initial Deposit:");
        JTextField txtInitialDeposit = new JTextField(15);

        JButton btnOpen = new JButton("Open Account");
        btnOpen.setBackground(new Color(144, 238, 144)); // Light green
        btnOpen.addActionListener(e -> {
            String accountType = txtAccountType.getText();
            double initialDeposit = Double.parseDouble(txtInitialDeposit.getText());
            Account newAccount = null;

            switch (accountType.toLowerCase()) {
                case "savings":
                    newAccount = new SavingsAccount(bank.generateAccountNumber(), loggedInCustomer, initialDeposit, 0.04);
                    break;
                case "current":
                    String overdraftLimitInput = JOptionPane.showInputDialog("Enter overdraft limit:");
                    double overdraftLimit = Double.parseDouble(overdraftLimitInput);
                    newAccount = new CurrentAccount(bank.generateAccountNumber(), loggedInCustomer, initialDeposit, overdraftLimit);
                    break;
                case "loan":
                    newAccount = new LoanAccount(bank.generateAccountNumber(), loggedInCustomer, initialDeposit, 0.07);
                    break;
                default:
                    JOptionPane.showMessageDialog(openAccountDialog, "Invalid account type!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            loggedInCustomer.openAccount(newAccount);
            bank.openAccount(loggedInCustomer, newAccount);
            JOptionPane.showMessageDialog(openAccountDialog, "Account opened successfully!");
            openAccountDialog.dispose();
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        openAccountDialog.add(lblAccountType, gbc);

        gbc.gridx = 1;
        openAccountDialog.add(txtAccountType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        openAccountDialog.add(lblInitialDeposit, gbc);

        gbc.gridx = 1;
        openAccountDialog.add(txtInitialDeposit, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        openAccountDialog.add(btnOpen, gbc);

        openAccountDialog.setLocationRelativeTo(frame);
        openAccountDialog.setVisible(true);
    }

    private void depositDialog() {
        // Similar to deposit functionality in Main.java
        String accountNumber = JOptionPane.showInputDialog(frame, "Enter account number:");
        Account account = bank.findAccountByNumber(accountNumber);
        if (account == null) {
            JOptionPane.showMessageDialog(frame, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String amountInput = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
        double amount = Double.parseDouble(amountInput);
        account.deposit(amount);
        JOptionPane.showMessageDialog(frame, "Amount deposited successfully!");
    }

    private void withdrawDialog() {
        // Similar to withdraw functionality in Main.java
        String accountNumber = JOptionPane.showInputDialog(frame, "Enter account number:");
        Account account = bank.findAccountByNumber(accountNumber);
        if (account == null) {
            JOptionPane.showMessageDialog(frame, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String amountInput = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
        double amount = Double.parseDouble(amountInput);
        account.withdraw(amount);
        JOptionPane.showMessageDialog(frame, "Amount withdrawn successfully!");
    }

    private void transferDialog() {
        // Similar to transfer functionality in Main.java
        String fromAccountNumber = JOptionPane.showInputDialog(frame, "Enter your account number:");
        String toAccountNumber = JOptionPane.showInputDialog(frame, "Enter recipient account number:");
        String amountInput = JOptionPane.showInputDialog(frame, "Enter transfer amount:");
        double amount = Double.parseDouble(amountInput);
        bank.transferFunds(fromAccountNumber, toAccountNumber, amount);
        JOptionPane.showMessageDialog(frame, "Transfer completed successfully!");
    }

    private void checkBalanceDialog() {
        // Similar to check balance functionality in Main.java
        String accountNumber = JOptionPane.showInputDialog(frame, "Enter account number:");
        Account account = bank.findAccountByNumber(accountNumber);
        if (account == null) {
            JOptionPane.showMessageDialog(frame, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Current balance: $" + account.getBalance());
        }
    }

    private void transactionHistoryDialog() {
        // Similar to transaction history functionality in Main.java
        String accountNumber = JOptionPane.showInputDialog(frame, "Enter account number:");
        Account account = bank.findAccountByNumber(accountNumber);
        if (account != null) {
            account.printTransactionHistory();
        } else {
            JOptionPane.showMessageDialog(frame, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateInterest() {
        // Call the calculate interest method from Bank
        bank.calculateMonthlyInterest();
        JOptionPane.showMessageDialog(frame, "Monthly interest calculated!");
    }

    private void logout() {
        loggedInCustomer = null;
        JOptionPane.showMessageDialog(frame, "You have been logged out.");
        showLoginDialog(); // Show login dialog again
    }
}
