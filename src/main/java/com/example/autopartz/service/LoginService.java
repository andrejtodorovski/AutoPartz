package com.example.autopartz.service;

import com.example.autopartz.model.User;
import com.example.autopartz.model.Warehouse;

public interface LoginService {
    void register(String name, String username, String email, String number, String password, String role);
    User login(String username, String password);
    void registerWarehouseman(String name, String username, String email, String number, String password, String role, Warehouse warehouse);
}
