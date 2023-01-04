package com.example.autopartz.controller;

import com.example.autopartz.repository.OrdersForUserRepository;
import com.example.autopartz.repository.RepairsForUserRepository;
import com.example.autopartz.repository.ReviewsForUserRepository;
import com.example.autopartz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class UserController {
    private final OrdersForUserRepository ordersForUserRepository;
    private final RepairsForUserRepository repairsForUserRepository;
    private final ReviewsForUserRepository reviewsForUserRepository;
    private final UserService userService;
    public UserController(OrdersForUserRepository ordersForUserRepository, RepairsForUserRepository repairsForUserRepository, ReviewsForUserRepository reviewsForUserRepository, UserService userService) {
        this.ordersForUserRepository = ordersForUserRepository;
        this.repairsForUserRepository = repairsForUserRepository;
        this.reviewsForUserRepository = reviewsForUserRepository;
        this.userService = userService;
    }
    @GetMapping("orders")
    public String getOrdersForUser(Model model, HttpServletRequest request){
        Integer id = userService.findByUsername(request.getRemoteUser()).getID_user();
        model.addAttribute("userOrders",ordersForUserRepository.findAllByUserid(id));
        model.addAttribute("bodyContent","ordersForUser");
        return "master-template";
    }
    @GetMapping("repairs")
    public String getRepairsForUser(Model model, HttpServletRequest request){
        Integer id = userService.findByUsername(request.getRemoteUser()).getID_user();
        model.addAttribute("userRepairs",repairsForUserRepository.findAllByUserid(id));
        model.addAttribute("bodyContent","repairsForUser");
        return "master-template";
    }
    @GetMapping("reviews")
    public String getReviewsForUser(Model model,HttpServletRequest request){
        Integer id = userService.findByUsername(request.getRemoteUser()).getID_user();
        model.addAttribute("userReviews",reviewsForUserRepository.findAllByUserid(id));
        model.addAttribute("bodyContent","reviewsForUser");
        return "master-template";
    }
}
