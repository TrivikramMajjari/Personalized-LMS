package com.example.lms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Homepage;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    // Implement dashboard related endpoints
    @GetMapping("/homepage")
    public Homepage getHomepage() {
        // For demo, return static message
        return new Homepage("Welcome to LMS!");
    }
}
