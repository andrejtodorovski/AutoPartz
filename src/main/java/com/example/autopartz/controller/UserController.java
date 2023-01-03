package com.example.autopartz.controller;

import com.example.autopartz.model.*;
import com.example.autopartz.repository.*;
import com.example.autopartz.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final RepairShopReviewSummaryRepository repairShopReviewSummaryRepository;
    private final PartsForCarTypeAndCategoryRepository partsForCarTypeAndCategoryRepository;

    private final OrdersForUserRepository ordersForUserRepository;
    private final RepairsForUserRepository repairsForUserRepository;
    private final ReviewsForUserRepository reviewsForUserRepository;
    public UserController(UserService userService, RepairShopReviewSummaryRepository repairShopReviewSummaryRepository, PartsForCarTypeAndCategoryRepository partsForCarTypeAndCategoryRepository, OrdersForUserRepository ordersForUserRepository, RepairsForUserRepository repairsForUserRepository, ReviewsForUserRepository reviewsForUserRepository) {
        this.userService = userService;
        this.repairShopReviewSummaryRepository = repairShopReviewSummaryRepository;
        this.partsForCarTypeAndCategoryRepository = partsForCarTypeAndCategoryRepository;
        this.ordersForUserRepository = ordersForUserRepository;
        this.repairsForUserRepository = repairsForUserRepository;
        this.reviewsForUserRepository = reviewsForUserRepository;
    }
    @GetMapping("reportTest")
    public List<RepairShopReviewsSummary> getRepairShopReport(){
        return repairShopReviewSummaryRepository.findAll();
    }
    @GetMapping("usersTest")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }
    @GetMapping("parts")
    public List<PartsForCarTypeAndCategory> getPartsForCarTypeAndCategory(@RequestParam String cartype, @RequestParam String category){
        return partsForCarTypeAndCategoryRepository.findAllByCartypeAndCategory(cartype,category);
    }
    @GetMapping("orders/{id}")
    public List<OrdersForUser> getOrdersForUser(@PathVariable Long id){
        return ordersForUserRepository.findAllByUserid(id);
    }
    @GetMapping("repairs/{id}")
    public List<RepairsForUser> getRepairsForUser(@PathVariable Long id){
        return repairsForUserRepository.findAllByUserid(id);
    }
    @GetMapping("reviews/{id}")
    public List<ReviewsForUser> getReviewsForUser(@PathVariable Long id){
        return reviewsForUserRepository.findAllByUserid(id);
    }
}
