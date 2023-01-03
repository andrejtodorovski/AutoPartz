package com.example.autopartz.controller;

import com.example.autopartz.model.*;
import com.example.autopartz.model.views.*;
import com.example.autopartz.repository.*;
import com.example.autopartz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    public String getOrdersForUser(@PathVariable Long id, Model model){
        model.addAttribute("userOrders",ordersForUserRepository.findAllByUserid(id));
        return "ordersForUser";
    }
    @GetMapping("repairs/{id}")
    public String getRepairsForUser(@PathVariable Long id,Model model){
        model.addAttribute("userRepairs",repairsForUserRepository.findAllByUserid(id));
        return "repairsForUser";
    }
    @GetMapping("reviews/{id}")
    public String getReviewsForUser(@PathVariable Long id, Model model){
        model.addAttribute("userReviews",reviewsForUserRepository.findAllByUserid(id));
        return "reviewsForUser";
    }
}
