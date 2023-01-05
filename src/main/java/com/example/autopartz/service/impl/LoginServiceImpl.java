package com.example.autopartz.service.impl;

import com.example.autopartz.model.*;
import com.example.autopartz.repository.UserRepository;
import com.example.autopartz.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String name, String username, String email, String number, String password, String role) {
        if (Objects.equals(role, "client")) {
            userRepository.save(new Client(username, name, email, password, number));
        }
        else {
            userRepository.save(new Deliveryman(username, name, email, password, number));
        }
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findAllByUsernameAndPassword(username,password).stream().findFirst().orElseThrow(RuntimeException::new);
    }

    @Override
    public void registerWarehouseman(String name, String username, String email, String number, String password, String role, Warehouse warehouse) {
        userRepository.save(new Warehouseman(username, name, email, password, number, warehouse));

    }

//    @Override
//    public User findByUsername(String username) {
//        return userRepository.findAllByUsername(username).stream().findFirst().orElseThrow(RuntimeException::new);
//    }
}
