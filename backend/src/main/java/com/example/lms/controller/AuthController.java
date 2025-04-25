package com.example.lms.controller;

import com.example.lms.dto.LoginRequest;
import com.example.lms.dto.SignupRequest;
import com.example.lms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
    try {
        authService.signup(signupRequest);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("Signup failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = authService.login(loginRequest);
        if (isAuthenticated) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
    // @GetMapping("/signup")
    // public String signupForm() {
    //     return "<html><body><h2>Signup Page</h2>"
    //          + "<form method='post' action='/api/auth/signup'>"
    //          + "Username: <input name='username'/><br/>"
    //          + "Email: <input name='email'/><br/>"
    //          + "Password: <input type='password' name='password'/><br/>"
    //          + "<button type='submit'>Sign Up</button>"
    //          + "</form></body></html>";
    // }

    // @GetMapping("/login")
    // public String loginForm() {
    //     return "<html><body><h2>Login Page</h2>"
    //          + "<form method='post' action='/api/auth/login'>"
    //          + "Username: <input name='username'/><br/>"
    //          + "Password: <input type='password' name='password'/><br/>"
    //          + "<button type='submit'>Login</button>"
    //          + "</form></body></html>";
    // }
}
