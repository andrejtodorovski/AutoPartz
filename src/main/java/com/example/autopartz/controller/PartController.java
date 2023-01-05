package com.example.autopartz.controller;

import com.example.autopartz.model.Client;
import com.example.autopartz.model.Order;
import com.example.autopartz.model.Part;
import com.example.autopartz.model.User;
import com.example.autopartz.model.manytomany.OrderContainsPart;
import com.example.autopartz.repository.OrderContainsPartRepository;
import com.example.autopartz.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/part")
public class PartController {
    private final PartService partService;
    private final RepairShopService repairShopService;
    private final PriceService priceService;
    private final OrderService orderService;
    private final UserService userService;
    private final OrderContainsPartRepository orderContainsPartRepository;
    public PartController(PartService partService, RepairShopService repairShopService, PriceService priceService, OrderService orderService, UserService userService, OrderContainsPartRepository orderContainsPartRepository) {
        this.partService = partService;
        this.repairShopService = repairShopService;
        this.priceService = priceService;
        this.orderService = orderService;
        this.userService = userService;
        this.orderContainsPartRepository = orderContainsPartRepository;
    }
    @GetMapping("/{id}")
    public String getPartPage(@PathVariable Integer id, Model model){
        Part temp = partService.findById(id);
        Integer amount = priceService.findPriceForPart(temp).stream().findFirst().orElseThrow(RuntimeException::new).getAmount();
        model.addAttribute("part",temp);
        model.addAttribute("amount",amount);
        model.addAttribute("bodyContent","partinfo");
        return "master-template";
    }
    @GetMapping("/delivery")
    public String getDeliveryPage(Model model){
        model.addAttribute("repairShops",repairShopService.findAll());
        model.addAttribute("bodyContent","deliveryForPart");
        return "master-template";
    }
    @PostMapping("/repairshopdelivery")
    public void setRepairShopDelivery(@RequestParam String name, HttpServletResponse response){
        // insert into project.repair (vin, id_repair_shop, id_service_book) values (1111,3,1)
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/homedelivery")
    public void setHomeDelivery(@RequestParam String address, HttpServletResponse response){
        // insert into delivery (delivery_status, delivery_address,id_user,id_order) values ('in progress','Aerodrom',4,1)
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/addToOrder/{id}")
    public void addToOrder(@PathVariable Integer id,@RequestParam Integer quantity, HttpSession session, HttpServletResponse response, HttpServletRequest request){
        if(session.getAttribute("order")==null){
            User u = userService.findByUsername(request.getRemoteUser());
            Order newOrder = orderService.create((Client) u);
            session.setAttribute("order",newOrder);
        }
        Order order = (Order) session.getAttribute("order");
        orderContainsPartRepository.save(new OrderContainsPart(id,order.getID_order(),quantity));
        try {
            response.sendRedirect("/products");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
