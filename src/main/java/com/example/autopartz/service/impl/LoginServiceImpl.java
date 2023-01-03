package com.example.autopartz.service.impl;

import com.example.autopartz.model.User;
import com.example.autopartz.repository.UserRepository;
import com.example.autopartz.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String name, String username, String email, String number, String password) {
        return userRepository.save(new User(username,name,email,password,number));
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findAllByUsernameAndPassword(username,password).stream().findFirst().orElseThrow(RuntimeException::new);
    }

//    @Override
//    public User findByUsername(String username) {
//        return userRepository.findAllByUsername(username).stream().findFirst().orElseThrow(RuntimeException::new);
//    }
}
