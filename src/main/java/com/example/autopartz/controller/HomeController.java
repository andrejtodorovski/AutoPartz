package com.example.autopartz.controller;

import com.example.autopartz.model.User;
import com.example.autopartz.repository.PartsForCarTypeAndCategoryRepository;
import com.example.autopartz.repository.RepairShopReviewSummaryRepository;
import com.example.autopartz.service.CarService;
import com.example.autopartz.service.CategoryService;
import com.example.autopartz.service.LoginService;
import com.example.autopartz.service.PartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoginService loginService;
    private final PartService partService;
    private final PartsForCarTypeAndCategoryRepository partsForCarTypeAndCategoryRepository;
    private final CarService carService;
    private final CategoryService categoryService;
    private final RepairShopReviewSummaryRepository repairShopReviewSummaryRepository;

    public HomeController(LoginService loginService, PartService partService, PartsForCarTypeAndCategoryRepository partsForCarTypeAndCategoryRepository, CarService carService, CategoryService categoryService, RepairShopReviewSummaryRepository repairShopReviewSummaryRepository) {
        this.loginService = loginService;
        this.partService = partService;
        this.partsForCarTypeAndCategoryRepository = partsForCarTypeAndCategoryRepository;
        this.carService = carService;
        this.categoryService = categoryService;
        this.repairShopReviewSummaryRepository = repairShopReviewSummaryRepository;
    }

    @GetMapping()
    public String getHomePage(Model model, HttpServletRequest request){
        model.addAttribute("bodyContent","home");
        model.addAttribute("user",request.getRemoteUser());
        return "master-template";
    }
    @GetMapping("/products")
    public String getProducts(Model model){
        model.addAttribute("parts",partService.findAll());
        model.addAttribute("cars",carService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("bodyContent","products");
        return "master-template";
    }
    @GetMapping("/services")
    public String getServices(Model model){
        model.addAttribute("services",repairShopReviewSummaryRepository.findAll());
        model.addAttribute("bodyContent","services");
        return "master-template";
    }
    @GetMapping("/filtered")
    public String getPartsForCarTypeAndCategory(@RequestParam String cartype, @RequestParam String category, Model model){
        model.addAttribute("filtered", partsForCarTypeAndCategoryRepository.findAllByCartypeAndCategory(cartype,category));
        model.addAttribute("bodyContent","filteredParts");
        return "master-template";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent","login");
        return "master-template";
    }
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("bodyContent","register");
        return "master-template";
    }
    @PostMapping("/login")
    public void handleLogin(@RequestParam String username, @RequestParam String password){
        User u = loginService.login(username,password);
        System.out.println(u.getName_user());
    }
    @PostMapping("/register")
    public void handleRegister(@RequestParam String username, @RequestParam String name,
                               @RequestParam String password, @RequestParam String email,
                               @RequestParam String number){
        User u = loginService.register(name,username,email,number,password);
    }
}
