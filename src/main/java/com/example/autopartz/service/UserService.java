package com.example.autopartz.service;

import com.example.autopartz.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findByUsername(String username);

}
