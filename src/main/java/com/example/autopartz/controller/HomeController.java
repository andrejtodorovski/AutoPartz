package com.example.autopartz.controller;

import com.example.autopartz.model.User;
import com.example.autopartz.repository.PartsForCarTypeAndCategoryRepository;
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

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoginService loginService;
    private final PartService partService;
    private final PartsForCarTypeAndCategoryRepository partsForCarTypeAndCategoryRepository;
    private final CarService carService;
    private final CategoryService categoryService;

    public HomeController(LoginService loginService, PartService partService, PartsForCarTypeAndCategoryRepository partsForCarTypeAndCategoryRepository, CarService carService, CategoryService categoryService) {
        this.loginService = loginService;
        this.partService = partService;
        this.partsForCarTypeAndCategoryRepository = partsForCarTypeAndCategoryRepository;
        this.carService = carService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String getHomePage(Model model){
        model.addAttribute("parts",partService.findAll());
        model.addAttribute("cars",carService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("bodyContent","homepage");
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
