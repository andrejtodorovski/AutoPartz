package com.example.autopartz.controller;

import com.example.autopartz.model.*;
import com.example.autopartz.model.DTO.OrderInfo;
import com.example.autopartz.model.manytomany.OrderContainsPart;
import com.example.autopartz.model.manytomany.PartIsInStockInWarehouse;
import com.example.autopartz.model.views.DeliveriesInProgress;
import com.example.autopartz.model.views.PartsForCarTypeAndCategory;
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
    private final UserService userService;
    private final DeliveriesInProgressRepository deliveriesInProgressRepository;
    private final DeliveryService deliveryService;
    private final PartIsInStockInWarehouseRepository partIsInStockInWarehouseRepository;
    private final CarCategoryReportRepository carCategoryReportRepository;
    private final PartManufacturersReportRepository partManufacturersReportRepository;
    private final MostPurchasedPartRepository mostPurchasedPartRepository;
    public HomeController(LoginService loginService, PartService partService, PartsForCarTypeAndCategoryRepository partsForCarTypeAndCategoryRepository, CarService carService, CategoryService categoryService, RepairShopReviewSummaryRepository repairShopReviewSummaryRepository, WarehouseRepository warehouseRepository,
                          OrderContainsPartRepository orderContainsPartRepository, OrderService orderService, UserService userService, DeliveriesInProgressRepository deliveriesInProgressRepository, DeliveryService deliveryService, PartIsInStockInWarehouseRepository partIsInStockInWarehouseRepository, CarCategoryReportRepository carCategoryReportRepository, PartManufacturersReportRepository partManufacturersReportRepository, MostPurchasedPartRepository mostPurchasedPartRepository) {
        this.loginService = loginService;
        this.partService = partService;
        this.partsForCarTypeAndCategoryRepository = partsForCarTypeAndCategoryRepository;
        this.carService = carService;
        this.categoryService = categoryService;
        this.repairShopReviewSummaryRepository = repairShopReviewSummaryRepository;
        this.warehouseRepository = warehouseRepository;
        this.orderContainsPartRepository = orderContainsPartRepository;
        this.orderService = orderService;
        this.userService = userService;
        this.deliveriesInProgressRepository = deliveriesInProgressRepository;
        this.deliveryService = deliveryService;
        this.partIsInStockInWarehouseRepository = partIsInStockInWarehouseRepository;
        this.carCategoryReportRepository = carCategoryReportRepository;
        this.partManufacturersReportRepository = partManufacturersReportRepository;
        this.mostPurchasedPartRepository = mostPurchasedPartRepository;
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
            model.addAttribute("parts",orderService.findById(o.getOrderid()).getPartList());
        }
        model.addAttribute("bodyContent","currentOrder");
        return "master-template";
    }
    @GetMapping("/filtered")
    public String getPartsForCarTypeAndCategory(@RequestParam String cartype, @RequestParam String category, Model model){
        List<PartsForCarTypeAndCategory> tmp = partsForCarTypeAndCategoryRepository.findAllByCartypeAndCategory(cartype,category);
        if(tmp.size()==0){
            model.addAttribute("hasError",true);
            model.addAttribute("error","Не постојат такви производи, обидете се повторно");
        }
        else {
            model.addAttribute("hasError",false);
            model.addAttribute("filtered", tmp);
        }
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
        System.out.println(u.getName());
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
    @GetMapping("/access_denied")
    public String accessDenied(Model model){
        model.addAttribute("bodyContent","access_denied");
        return "master-template";
    }
    @GetMapping("/myWarehouse")
    public String myWarehouse(Model model, HttpServletRequest request){
        Warehouseman whm = (Warehouseman) userService.findByUsername(request.getRemoteUser());
        Warehouse warehouse = whm.getWarehouse();
        List<PartIsInStockInWarehouse> partIsInStockInWarehouseList = partIsInStockInWarehouseRepository.findAllByWarehouseid(warehouse.getID_warehouse());
        model.addAttribute("bodyContent","myWarehouse");
        model.addAttribute("warehouse",warehouse);
        model.addAttribute("parts", partIsInStockInWarehouseList);
        return "master-template";
    }
    @GetMapping("myDeliveries")
    public String myDeliveries(Model model, HttpServletRequest request){
        Deliveryman dm = (Deliveryman) userService.findByUsername(request.getRemoteUser());
        List<Delivery> deliveries = deliveryService.findAllByDeliverer(dm);
        model.addAttribute("bodyContent","myDeliveries");
        model.addAttribute("deliveries",deliveries);
        return "master-template";
    }
    @GetMapping("myNextDeliveries")
    public String myNextDeliveries(Model model, HttpServletRequest request){
        Deliveryman dm = (Deliveryman) userService.findByUsername(request.getRemoteUser());
        List<DeliveriesInProgress> ldip = deliveriesInProgressRepository.findAllByUserid(dm.getId());
        if(ldip.size()==0){
            model.addAttribute("hasError",true);
            model.addAttribute("error","Сите достави се завршени");
        }
        else {
            model.addAttribute("hasError",false);
            model.addAttribute("deliveries", deliveriesInProgressRepository.findAllByUserid(dm.getId()));
        }
        model.addAttribute("bodyContent","myNextDeliveries");
        return "master-template";
    }
    @PostMapping("/finishDelivery/{id}")
    public void finishDelivery(@PathVariable Integer id, Model model, HttpServletResponse response){
        Delivery d = deliveryService.findByOrder(orderService.findById(id));
        d.setStatus("finished");
        deliveryService.update(d);
        try {
            response.sendRedirect("/myDeliveries");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/order/{id}")
    public String getOrderInfo(@PathVariable Integer id, Model model){
        List<OrderContainsPart> list = orderContainsPartRepository.findAllByOrderid(id);
        List<OrderInfo> partList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            OrderInfo oi = new OrderInfo(partService.findById(list.get(i).getPartid()).getName(),
                    list.get(i).getQuantity_order(),partService.findById(list.get(i).getPartid()).getManufacturer().getName());
            partList.add(oi);
        }
        model.addAttribute("parts",partList);
        model.addAttribute("o",orderService.findById(id));
        model.addAttribute("bodyContent","orderInfo");
        return "master-template";
    }
    @GetMapping("/carCategoryReport")
    public String getCarCategoryInfo(Model model){
        model.addAttribute("data",carCategoryReportRepository.findAll());
        model.addAttribute("bodyContent","carCategoryReport");
        return "master-template";
    }
    @GetMapping("/partManufacturersReport")
    public String getPartManufacturersReport(Model model){
        model.addAttribute("data",partManufacturersReportRepository.findAll());
        model.addAttribute("bodyContent","partManufacturersReport");
        return "master-template";

    }
    @GetMapping("/mostPurchasedPart")
    public String getMostPurchasedPart(Model model){
        model.addAttribute("data",mostPurchasedPartRepository.findAll());
        model.addAttribute("bodyContent","mostPurchasedPart");
        return "master-template";
    }
}
