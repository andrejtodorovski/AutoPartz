package com.example.autopartz.controller;

import com.example.autopartz.model.*;
import com.example.autopartz.model.manytomany.OrderContainsPart;
import com.example.autopartz.model.manytomany.RsForCm;
import com.example.autopartz.repository.*;
import com.example.autopartz.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/part")
public class PartController {
    private final PartService partService;
    private final RepairShopService repairShopService;
    private final PriceService priceService;
    private final OrderService orderService;
    private final UserService userService;
    private final OrderContainsPartRepository orderContainsPartRepository;
    private final DeliveryRepository deliveryRepository;
    private final RepairRepository repairRepository;
    private final CarSampleRepository carSampleRepository;
    private final ServiceBookRepository serviceBookRepository;
    private final DeliverymanRepository deliverymanRepository;
    private final RsForCmRepository rsForCmRepository;
    public PartController(PartService partService, RepairShopService repairShopService, PriceService priceService, OrderService orderService, UserService userService, OrderContainsPartRepository orderContainsPartRepository, DeliveryRepository deliveryRepository, RepairRepository repairRepository, CarSampleRepository carSampleRepository, ServiceBookRepository serviceBookRepository, DeliverymanRepository deliverymanRepository, RsForCmRepository rsForCmRepository) {
        this.partService = partService;
        this.repairShopService = repairShopService;
        this.priceService = priceService;
        this.orderService = orderService;
        this.userService = userService;
        this.orderContainsPartRepository = orderContainsPartRepository;
        this.deliveryRepository = deliveryRepository;
        this.repairRepository = repairRepository;
        this.carSampleRepository = carSampleRepository;
        this.serviceBookRepository = serviceBookRepository;
        this.deliverymanRepository = deliverymanRepository;
        this.rsForCmRepository = rsForCmRepository;
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
    public String getDeliveryPage(Model model, HttpServletRequest request){
        Client cl = (Client) userService.findByUsername(request.getRemoteUser());
        List<CarSample> cs = carSampleRepository.findAllByClient(cl);
        model.addAttribute("cars",cs);
        if(cs.size()==0){
                model.addAttribute("hasError",true);
                model.addAttribute("error","Внеси твоја кола");
        }
        else {
            model.addAttribute("hasError",false);
        }
        model.addAttribute("bodyContent","deliveryForPart");
        return "master-template";
    }
    @PostMapping("/repairshopdelivery")
    public void setRepairShopDelivery(@RequestParam Integer vin, HttpServletResponse response, HttpSession session, HttpServletRequest request){
        // insert into project.repair (vin, id_repair_shop, id_service_book) values (1111,3,1)
        session.setAttribute("carVin",vin);
        try {
            response.sendRedirect("/part/chooseRepairShop");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/chooseRepairShop")
    public String chooseRepairShop(Model model, HttpSession session){
        if(session.getAttribute("carVin")==null){
            model.addAttribute("hasError",true);
            model.addAttribute("error","Внеси твоја кола");
        }
        else {
            model.addAttribute("hasError", false);
            CarSample cs = carSampleRepository.findById((Integer) session.getAttribute("carVin")).get();
            Integer idCM = cs.getCar().getCar_manufacturer().getID_car_manufacturer();
            List<RsForCm> rsForCm = rsForCmRepository.findAllByCmid(idCM);
            List<RepairShop> newRepairShopList = new ArrayList<>();
            for (RsForCm forCm : rsForCm) {
                newRepairShopList.add(repairShopService.getById(forCm.getRsid()));
            }
            model.addAttribute("authorized", newRepairShopList);
        }
        model.addAttribute("bodyContent", "chooseRepairShop");
        return "master-template";
    }
    @PostMapping("/chooseRepairShop")
    public void chooseRepairShop(@RequestParam Integer rs,HttpSession session, HttpServletResponse response){
        Order o = (Order) session.getAttribute("order");
        CarSample cs = carSampleRepository.findById((Integer)session.getAttribute("carVin")).get();
        ServiceBook sb = serviceBookRepository.findByCarSample(cs);
        RepairShop repairShop = repairShopService.getById(rs);
        repairRepository.save(new Repair(o,repairShop,sb));
//        o.setOrder_status("finished");
//        orderService.save(o);
        session.removeAttribute("order");
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/homedelivery")
    public void setHomeDelivery(@RequestParam String address, HttpServletResponse response, HttpSession session){
        // insert into delivery (delivery_status, delivery_address,id_user,id_order) values ('in progress','Aerodrom',4,1)
        Order o = (Order) session.getAttribute("order");
        List<Deliveryman> deliverymanList = deliverymanRepository.findAll().stream().filter(dm->dm.getAuthorities().contains(Role.ROLE_DELIVERYMAN)).toList();
        int num = deliverymanList.size();
        int deliverer =  (int) ((Math.random() * (num)));
        Deliveryman dm = deliverymanList.get(deliverer);
        deliveryRepository.save(new Delivery("in progress",address,dm,o));
//        o.setOrder_status("finished");
//        orderService.save(o);
        session.removeAttribute("order");
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
        orderContainsPartRepository.save(new OrderContainsPart(id,order.getOrderid(),quantity));
        try {
            response.sendRedirect("/products");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
