package com.example.autopartz.controller;

import com.example.autopartz.model.*;
import com.example.autopartz.model.views.RepairsForUser;
import com.example.autopartz.repository.*;
import com.example.autopartz.service.CarService;
import com.example.autopartz.service.RepairService;
import com.example.autopartz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final OrdersForUserRepository ordersForUserRepository;
    private final RepairsForUserRepository repairsForUserRepository;
    private final ReviewsForUserRepository reviewsForUserRepository;
    private final UserService userService;
    private final CarService carService;
    private final CarSampleRepository carSampleRepository;
    private final ServiceBookRepository serviceBookRepository;
    private final RepairService repairService;
    private final ReviewRepository reviewRepository;
    private final RepairRepository repairRepository;
    private final OrderRepository orderRepository;

    public UserController(OrdersForUserRepository ordersForUserRepository, RepairsForUserRepository repairsForUserRepository, ReviewsForUserRepository reviewsForUserRepository, UserService userService, CarService carService, CarSampleRepository carSampleRepository, ServiceBookRepository serviceBookRepository, RepairService repairService, ReviewRepository reviewRepository,
                          RepairRepository repairRepository,
                          OrderRepository orderRepository) {
        this.ordersForUserRepository = ordersForUserRepository;
        this.repairsForUserRepository = repairsForUserRepository;
        this.reviewsForUserRepository = reviewsForUserRepository;
        this.userService = userService;
        this.carService = carService;
        this.carSampleRepository = carSampleRepository;
        this.serviceBookRepository = serviceBookRepository;
        this.repairService = repairService;
        this.reviewRepository = reviewRepository;
        this.repairRepository = repairRepository;
        this.orderRepository = orderRepository;
    }
    @GetMapping("orders")
    public String getOrdersForUser(Model model, HttpServletRequest request){
        Integer id = userService.findByUsername(request.getRemoteUser()).getId();
        model.addAttribute("userOrders",ordersForUserRepository.findAllByUserid(id));
        model.addAttribute("bodyContent","ordersForUser");
        return "master-template";
    }
    @GetMapping("repairs")
    public String getRepairsForUser(Model model, HttpServletRequest request){
        Integer id = userService.findByUsername(request.getRemoteUser()).getId();
        model.addAttribute("userRepairs",repairsForUserRepository.findAllByUserid(id));
        List<Boolean> hasReview = new ArrayList<>(100);
        for (int i = 0; i < orderRepository.findAll().size(); i++) {
            hasReview.add(false);
        }
        for(RepairsForUser rfu: repairsForUserRepository.findAll()){
            if(reviewRepository.findByRepair(repairService.findById(rfu.getRepairid()))==null){
                hasReview.set(rfu.getRepairid(),false);
            }
            else {
                hasReview.set(rfu.getRepairid(),true);
            }
        }
        model.addAttribute("hasReview", hasReview);
        model.addAttribute("bodyContent","repairsForUser");
        return "master-template";
    }
    @GetMapping("reviews")
    public String getReviewsForUser(Model model,HttpServletRequest request){
        Integer id = userService.findByUsername(request.getRemoteUser()).getId();
        model.addAttribute("userReviews",reviewsForUserRepository.findAllByUserid(id));
        model.addAttribute("bodyContent","reviewsForUser");
        return "master-template";
    }
    @GetMapping("/addCarSampleForUser")
    public String addCarSampleForUser(Model model){
        model.addAttribute("bodyContent","addCarSampleForUser");
        model.addAttribute("cars",carService.findAll());
        return "master-template";
    }
    @PostMapping("/addCarSampleForUser")
    public void addCarSampleForUser(@RequestParam Integer vin, @RequestParam Integer year, @RequestParam Integer power,
                                    @RequestParam Integer displacement, @RequestParam String fuel,
                                    @RequestParam Integer km, @RequestParam Integer cartype,
                                    HttpServletRequest request, HttpServletResponse response){
        CarSample cs = new CarSample(vin,year,power,displacement,fuel,km, (Client) userService.findByUsername(request.getRemoteUser()),carService.findById(cartype));
        carSampleRepository.save(cs);
        serviceBookRepository.save(new ServiceBook(cs));
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/repairs/addReview/{id}")
    public String addReviewFor(@PathVariable Integer id, Model model){
        model.addAttribute("bodyContent","addReviewPage");
        model.addAttribute("repair", repairService.findById(id));
        return "master-template";
    }
    @PostMapping("/repairs/addReview/{id}")
    public void addReview(@PathVariable Integer id, @RequestParam Integer rating, @RequestParam(required = false) String comment,
                          Model model, HttpServletResponse response, HttpServletRequest request){
        Client client = (Client) userService.findByUsername(request.getRemoteUser());
        Repair repair = repairService.findById(id);
        reviewRepository.save(new Review(rating,
                comment==null ? "" : comment, repair,client));
        try {
            response.sendRedirect("/repairs");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
