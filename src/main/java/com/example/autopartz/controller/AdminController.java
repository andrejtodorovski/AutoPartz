package com.example.autopartz.controller;

import com.example.autopartz.model.Role;
import com.example.autopartz.model.User;
import com.example.autopartz.model.Warehouseman;
import com.example.autopartz.repository.WarehousemanRepository;
import com.example.autopartz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final WarehousemanRepository warehousemanRepository;

    public AdminController(UserService userService, WarehousemanRepository warehousemanRepository) {
        this.userService = userService;
        this.warehousemanRepository = warehousemanRepository;
    }

    @GetMapping("/viewUsers")
    public String getAllUsers(Model model){
        List<User> pendingList = userService.findAllUsers().stream().filter(u->u.getAuthorities().contains(Role.ROLE_PENDING_DELIVERYMAN) || u.getAuthorities().contains(Role.ROLE_PENDING_WAREHOUSEMAN)).toList();
        model.addAttribute("users", pendingList);
        model.addAttribute("bodyContent", "viewUsers");
        return "master-template";
    }
    @PostMapping("/approve/{id}")
    public void approve(@PathVariable Integer id, HttpServletResponse response){
        if(Objects.equals(userService.findById(id).getAuthorities().stream().findFirst().get(),Role.ROLE_PENDING_WAREHOUSEMAN)){
            Warehouseman wh = (Warehouseman) userService.findById(id);
            wh.setEmployed_from(LocalDate.now());
            warehousemanRepository.save(wh);
            try {
                response.sendRedirect("/viewUsers");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
