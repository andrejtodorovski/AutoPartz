package com.example.autopartz.controller;

import com.example.autopartz.model.RepairShopReviewsSummary;
import com.example.autopartz.model.User;
import com.example.autopartz.repository.RepairShopReviewSummaryRepository;
import com.example.autopartz.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final RepairShopReviewSummaryRepository rspsr;

    public UserController(UserService userService, RepairShopReviewSummaryRepository rspsr) {
        this.userService = userService;
        this.rspsr = rspsr;
    }
    @GetMapping("reportTest")
    public List<RepairShopReviewsSummary> getRepairShopReport(){
        return rspsr.findAllByRsid(2L);
    }
    @GetMapping("usersTest")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }
}
