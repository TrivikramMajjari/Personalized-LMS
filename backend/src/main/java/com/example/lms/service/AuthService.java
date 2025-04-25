package com.example.lms.service;

import com.example.lms.dto.LoginRequest;
import com.example.lms.dto.SignupRequest;
import com.example.lms.entity.User;
import com.example.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public void signup(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(signupRequest.getPassword()); // In production, hash the password!
        userRepository.save(user);
    }

    public boolean login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            return false;
        }
        // In production, compare hashed passwords!
        return user.getPassword().equals(loginRequest.getPassword());
    }
}