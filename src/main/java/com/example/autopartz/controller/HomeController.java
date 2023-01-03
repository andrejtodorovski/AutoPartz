package com.example.autopartz.controller;

import com.example.autopartz.model.User;
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

    public HomeController(LoginService loginService, PartService partService) {
        this.loginService = loginService;
        this.partService = partService;
    }

    @GetMapping()
    public String getHomePage(Model model){
        model.addAttribute("parts",partService.findAll());
        return "homepage";
    }
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }
    @PostMapping("/login")
    public void handleLogin(@RequestParam String username, @RequestParam String password){
        User u = loginService.login(username,password);
    }
    @PostMapping("/register")
    public void handleRegister(@RequestParam String username, @RequestParam String name,
                               @RequestParam String password, @RequestParam String email,
                               @RequestParam String number){
        User u = loginService.register(name,username,email,number,password);
    }
}
