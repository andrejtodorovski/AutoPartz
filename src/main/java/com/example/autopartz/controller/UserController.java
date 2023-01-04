package com.example.autopartz.controller;

import com.example.autopartz.repository.OrdersForUserRepository;
import com.example.autopartz.repository.RepairsForUserRepository;
import com.example.autopartz.repository.ReviewsForUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
    private final OrdersForUserRepository ordersForUserRepository;
    private final RepairsForUserRepository repairsForUserRepository;
    private final ReviewsForUserRepository reviewsForUserRepository;
    public UserController(OrdersForUserRepository ordersForUserRepository, RepairsForUserRepository repairsForUserRepository, ReviewsForUserRepository reviewsForUserRepository) {
        this.ordersForUserRepository = ordersForUserRepository;
        this.repairsForUserRepository = repairsForUserRepository;
        this.reviewsForUserRepository = reviewsForUserRepository;
    }
    @GetMapping("orders/{id}")
    public String getOrdersForUser(@PathVariable Integer id, Model model){
        model.addAttribute("userOrders",ordersForUserRepository.findAllByUserid(id));
        model.addAttribute("bodyContent","ordersForUser");
        return "master-template";
    }
    @GetMapping("repairs/{id}")
    public String getRepairsForUser(@PathVariable Integer id,Model model){
        model.addAttribute("userRepairs",repairsForUserRepository.findAllByUserid(id));
        model.addAttribute("bodyContent","repairsForUser");
        return "master-template";
    }
    @GetMapping("reviews/{id}")
    public String getReviewsForUser(@PathVariable Integer id, Model model){
        model.addAttribute("userReviews",reviewsForUserRepository.findAllByUserid(id));
        model.addAttribute("bodyContent","reviewsForUser");
        return "master-template";
    }
}
