package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User registerUser(String username, String password);
    User authenticateUser(String username, String password);
}
