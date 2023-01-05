package com.example.autopartz.controller;

import com.example.autopartz.model.Client;
import com.example.autopartz.model.Order;
import com.example.autopartz.model.Part;
import com.example.autopartz.model.User;
import com.example.autopartz.model.manytomany.OrderContainsPart;
import com.example.autopartz.repository.OrderContainsPartRepository;
import com.example.autopartz.service.OrderService;
import com.example.autopartz.service.PartService;
import com.example.autopartz.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private final OrderService orderService;
    private final PartService partService;
    private final OrderContainsPartRepository orderContainsPartRepository;
    private final UserService userService;

    public TestController(OrderService orderService, PartService partService, OrderContainsPartRepository orderContainsPartRepository, UserService userService) {
        this.orderService = orderService;
        this.partService = partService;
        this.orderContainsPartRepository = orderContainsPartRepository;
        this.userService = userService;
    }

    @GetMapping("/m")
    public void getPartsInOrder(){

        User u = userService.findByUsername("client13");
        Order o = orderService.create((Client) u);
        orderContainsPartRepository.save(new OrderContainsPart(5,o.getID_order(),2));
        orderContainsPartRepository.save(new OrderContainsPart(7,o.getID_order(),1));
    }
    @GetMapping("/t")
    public List<Part> getParts(){
        return orderService.findById(24).getPartList();
    }
}
