package com.example.autopartz.service.impl;

import com.example.autopartz.model.User;
import com.example.autopartz.repository.UserRepository;
import com.example.autopartz.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findAllByUsername(username).stream().findFirst().orElseThrow(RuntimeException::new);
    }
}
