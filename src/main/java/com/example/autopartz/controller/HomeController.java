package com.example.autopartz.controller;

import com.example.autopartz.model.Order;
import com.example.autopartz.model.User;
import com.example.autopartz.model.Warehouse;
import com.example.autopartz.repository.OrderContainsPartRepository;
import com.example.autopartz.repository.PartsForCarTypeAndCategoryRepository;
import com.example.autopartz.repository.RepairShopReviewSummaryRepository;
import com.example.autopartz.repository.WarehouseRepository;
import com.example.autopartz.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoginService loginService;
    private final PartService partService;
    private final PartsForCarTypeAndCategoryRepository partsForCarTypeAndCategoryRepository;
    private final CarService carService;
    private final CategoryService categoryService;
    private final RepairShopReviewSummaryRepository repairShopReviewSummaryRepository;
    private final WarehouseRepository warehouseRepository;
    private final OrderContainsPartRepository orderContainsPartRepository;
    private final OrderService orderService;

    public HomeController(LoginService loginService, PartService partService, PartsForCarTypeAndCategoryRepository partsForCarTypeAndCategoryRepository, CarService carService, CategoryService categoryService, RepairShopReviewSummaryRepository repairShopReviewSummaryRepository, WarehouseRepository warehouseRepository,
                          OrderContainsPartRepository orderContainsPartRepository, OrderService orderService) {
        this.loginService = loginService;
        this.partService = partService;
        this.partsForCarTypeAndCategoryRepository = partsForCarTypeAndCategoryRepository;
        this.carService = carService;
        this.categoryService = categoryService;
        this.repairShopReviewSummaryRepository = repairShopReviewSummaryRepository;
        this.warehouseRepository = warehouseRepository;
        this.orderContainsPartRepository = orderContainsPartRepository;
        this.orderService = orderService;
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
    @GetMapping("/currentOrder")
    public String getCurrentOrder(Model model,HttpSession session){
        if(session.getAttribute("order")==null){
            model.addAttribute("hasError",true);
            model.addAttribute("error","Нарачката е празна");
        }
        else {
            Order o = (Order) session.getAttribute("order");
            model.addAttribute("hasError",false);
            model.addAttribute("order",o);
            model.addAttribute("parts",orderService.findById(o.getID_order()).getPartList());
        }
        model.addAttribute("bodyContent","currentOrder");
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
                               @RequestParam String password, @RequestParam String rpassword,
                               @RequestParam String email, @RequestParam String number,
                               @RequestParam String role, HttpServletResponse response, HttpSession session){
        System.out.println(username + name + password + rpassword + email + number + role);
        if(Objects.equals(role, "warehouseman")){
            session.setAttribute("username", username);
            session.setAttribute("name", name);
            session.setAttribute("password", password);
            session.setAttribute("rpassword", rpassword);
            session.setAttribute("email", email);
            session.setAttribute("number", number);
            try {
                response.sendRedirect("/registerWarehouseman");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            loginService.register(name, username, email, number, password, role);
            try {
                response.sendRedirect("/login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @GetMapping("/registerWarehouseman")
    public String getSelectPage(Model model){
        model.addAttribute("locations",warehouseRepository.findAll());
        model.addAttribute("bodyContent","selectWarehouse");
        return "master-template";
    }
    @PostMapping("/finishRegister")
    public void handleWarehousemanRegister(@RequestParam String location,Model model, HttpServletResponse response, HttpSession session){
        System.out.println("here?");
        String username = (String) session.getAttribute("username");
        String name = (String) session.getAttribute("name");
        String password = (String) session.getAttribute("password");
        String email = (String) session.getAttribute("email");
        String number = (String) session.getAttribute("number");
        Warehouse warehouse = warehouseRepository.findAllByLocation(location).stream().findFirst().orElseThrow(RuntimeException::new);
        loginService.registerWarehouseman(name,username,email,number,password,"warehouseman",warehouse);
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
