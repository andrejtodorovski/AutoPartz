package com.example.autopartz.controller;

import com.example.autopartz.model.*;
import com.example.autopartz.model.manytomany.PartIsAppropriateForCar;
import com.example.autopartz.model.manytomany.PartIsFromCategory;
import com.example.autopartz.model.manytomany.PartIsInStockInWarehouse;
import com.example.autopartz.repository.*;
import com.example.autopartz.service.PriceService;
import com.example.autopartz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final PartIsFromCategoryRepository partIsFromCategoryRepository;
    private final PartIsAppropriateForCarRepository partIsAppropriateForCarRepository;
    private final WarehousemanRepository warehousemanRepository;
    private final PartIsInStockInWarehouseRepository partIsInStockInWarehouseRepository;
    private final DeliverymanRepository deliverymanRepository;
    private final CategoryRepository categoryRepository;
    private final PartRepository partRepository;
    private final WarehouseRepository warehouseRepository;
    private final CarRepository carRepository;
    private final PartManufacturerRepository partManufacturerRepository;
    private final PriceService priceService;

    public AdminController(UserService userService, PartIsFromCategoryRepository partIsFromCategoryRepository, PartIsAppropriateForCarRepository partIsAppropriateForCarRepository, WarehousemanRepository warehousemanRepository, PartIsInStockInWarehouseRepository partIsInStockInWarehouseRepository, DeliverymanRepository deliverymanRepository, CategoryRepository categoryRepository, PartRepository partRepository, WarehouseRepository warehouseRepository, CarRepository carRepository, PartManufacturerRepository partManufacturerRepository, PriceService priceService) {
        this.userService = userService;
        this.partIsFromCategoryRepository = partIsFromCategoryRepository;
        this.partIsAppropriateForCarRepository = partIsAppropriateForCarRepository;
        this.warehousemanRepository = warehousemanRepository;
        this.partIsInStockInWarehouseRepository = partIsInStockInWarehouseRepository;
        this.deliverymanRepository = deliverymanRepository;
        this.categoryRepository = categoryRepository;
        this.partRepository = partRepository;
        this.warehouseRepository = warehouseRepository;
        this.carRepository = carRepository;
        this.partManufacturerRepository = partManufacturerRepository;
        this.priceService = priceService;
    }

    @GetMapping("/viewUsers")
    public String getAllUsers(Model model){
        List<User> pendingList = userService.findAllUsers().stream().filter(u->u.getAuthorities().contains(Role.ROLE_PENDING_DELIVERYMAN) || u.getAuthorities().contains(Role.ROLE_PENDING_WAREHOUSEMAN)).toList();
        if(pendingList.size()==0){
            model.addAttribute("hasError",true);
        }
        else {
            model.addAttribute("hasError",false);
            model.addAttribute("users", pendingList);
        }
        model.addAttribute("bodyContent", "viewUsers");
        return "master-template";
    }
    @PostMapping("/approve/{id}")
    public void approve(@PathVariable Integer id, HttpServletResponse response){
        if(Objects.equals(userService.findById(id).getAuthorities().stream().findFirst().get(),Role.ROLE_PENDING_WAREHOUSEMAN)){
            Warehouseman wh = (Warehouseman) userService.findById(id);
            wh.setEmployed_from(LocalDate.now());
            warehousemanRepository.save(wh);

        }
        else {
            Deliveryman dm = (Deliveryman) userService.findById(id);
            dm.setEmployed_from(LocalDate.now());
            deliverymanRepository.save(dm);
            try {
                response.sendRedirect("/viewUsers");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @GetMapping("/addPart")
    public String addPart(Model model){
        model.addAttribute("categories",categoryRepository.findAll());
        model.addAttribute("warehouses",warehouseRepository.findAll());
        model.addAttribute("cars",carRepository.findAll());
        model.addAttribute("manufacturers",partManufacturerRepository.findAll());
        model.addAttribute("bodyContent","addPart");
        return "master-template";
    }
    @PostMapping("/addPart")
    public void addPart(@RequestParam String name, @RequestParam(required = false) String description,
                        @RequestParam Integer manufacturer, @RequestParam List<Car> cars,
                        @RequestParam List<Category> categories, @RequestParam Integer warehouse,
                        @RequestParam Integer quantity, @RequestParam Integer amount, HttpServletResponse response){
        // Part(String name, String description, PartManufacturer manufacturer, List<Category> categoryList, List<Warehouse> warehouseList, List<Car> carList) {
        Part newPart = new Part(name, description==null ? "" : description, partManufacturerRepository.findById(manufacturer).get(),
                categories, List.of(warehouseRepository.findById(warehouse).get()),cars);
        partRepository.save(newPart);
        priceService.save(new Price(amount, LocalDate.now(),newPart));
        partIsInStockInWarehouseRepository.save(new PartIsInStockInWarehouse(newPart.getId(),warehouse,quantity));
        for (Category c:categories
             ) {
            partIsFromCategoryRepository.save(new PartIsFromCategory(newPart.getId(),c.getId()));
        }
        for (Car car:cars){
            partIsAppropriateForCarRepository.save(new PartIsAppropriateForCar(newPart.getId(),car.getId()));
        }
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
