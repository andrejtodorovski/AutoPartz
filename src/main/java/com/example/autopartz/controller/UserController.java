package com.example.autopartz.controller;

import com.example.autopartz.model.CarSample;
import com.example.autopartz.model.Client;
import com.example.autopartz.model.ServiceBook;
import com.example.autopartz.repository.*;
import com.example.autopartz.service.CarService;
import com.example.autopartz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public UserController(OrdersForUserRepository ordersForUserRepository, RepairsForUserRepository repairsForUserRepository, ReviewsForUserRepository reviewsForUserRepository, UserService userService, CarService carService, CarSampleRepository carSampleRepository, ServiceBookRepository serviceBookRepository) {
        this.ordersForUserRepository = ordersForUserRepository;
        this.repairsForUserRepository = repairsForUserRepository;
        this.reviewsForUserRepository = reviewsForUserRepository;
        this.userService = userService;
        this.carService = carService;
        this.carSampleRepository = carSampleRepository;
        this.serviceBookRepository = serviceBookRepository;
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
}
