package com.bankapp.models;
import java.io.FileWriter;
import java.io.IOException;

public class User {
    private String username;
    private String password; // In a real-world application, passwords should be hashed.

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        saveUserToCSV(); // Automatically save user details to CSV upon creation
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password); // Simple authentication check
    }

    // Method to save user details to CSV
    private void saveUserToCSV() {
        try (FileWriter writer = new FileWriter("users.csv", true)) {
            writer.append(username).append(',')
                  .append(password).append('\n');
        } catch (IOException e) {
            System.out.println("Error saving user to CSV: " + e.getMessage());
        }
    }
}
