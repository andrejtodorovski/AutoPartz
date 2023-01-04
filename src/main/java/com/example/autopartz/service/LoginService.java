package com.example.autopartz.service;

import com.example.autopartz.model.User;

public interface LoginService {
    User register(String name, String username, String email, String number, String password);
    User login(String username, String password);
}
