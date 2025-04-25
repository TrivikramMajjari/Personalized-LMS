package com.example.lms.dto;

// Updated SignupRequest with getUsername method
public class SignupRequest {
    private String email;
    private String password;
    private String username; // Added username field

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() { // Added getter for username
        return username;
    }

    public void setUsername(String username) { // Added setter for username
        this.username = username;
    }
}