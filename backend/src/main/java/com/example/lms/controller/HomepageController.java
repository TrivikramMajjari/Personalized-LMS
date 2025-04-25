package com.example.lms.controller;

import com.example.lms.entity.Homepage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomepageController {

    @GetMapping
    public Homepage getHomepage() {
        // For demo, return a static welcome message
        return new Homepage("Welcome to LMS! This is your personalized homepage.");
    }
}