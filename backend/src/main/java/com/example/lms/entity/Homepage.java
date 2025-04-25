package com.example.lms.entity;

import jakarta.persistence.*;

@Entity
public class Homepage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String welcomeMessage;

    // Add more fields as needed (e.g., userId, dashboard widgets, etc.)

    public Homepage() {}

    public Homepage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public Long getId() { return id; }
    public String getWelcomeMessage() { return welcomeMessage; }
    public void setWelcomeMessage(String welcomeMessage) { this.welcomeMessage = welcomeMessage; }
    
}
