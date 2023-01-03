package com.example.autopartz.controller;

import com.example.autopartz.model.Part;
import com.example.autopartz.service.PartService;
import com.example.autopartz.service.PriceService;
import com.example.autopartz.service.RepairShopService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/part")
public class PartController {
    private final PartService partService;
    private final RepairShopService repairShopService;
    private final PriceService priceService;
    public PartController(PartService partService, RepairShopService repairShopService, PriceService priceService) {
        this.partService = partService;
        this.repairShopService = repairShopService;
        this.priceService = priceService;
    }
    @GetMapping("/{id}")
    public String getPartPage(@PathVariable Long id, Model model){
        Part temp = partService.findById(id);
        Integer amount = priceService.findPriceForPart(temp).stream().findFirst().orElseThrow(RuntimeException::new).getAmount();
        model.addAttribute("part",temp);
        model.addAttribute("amount",amount);
        return "partinfo";
    }
    @GetMapping("/delivery/{id}")
    public String getDeliveryPage(@PathVariable Long id, Model model){
        model.addAttribute("repairShops",repairShopService.findAll());
        model.addAttribute("partId",id);
        return "deliveryForPart";
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
}
