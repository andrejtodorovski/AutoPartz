package com.example.autopartz.controller;

import com.example.autopartz.model.*;
import com.example.autopartz.model.manytomany.PartIsAppropriateForCar;
import com.example.autopartz.model.manytomany.PartIsFromCategory;
import com.example.autopartz.model.manytomany.PartIsInStockInWarehouse;
import com.example.autopartz.model.manytomany.RsForCm;
import com.example.autopartz.repository.*;
import com.example.autopartz.service.PriceService;
import com.example.autopartz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final CarManufacturerRepository carManufacturerRepository;
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
    private final RepairShopRepository repairShopRepository;
    private final RsForCmRepository rsForCmRepository;

    public AdminController(UserService userService, CarManufacturerRepository carManufacturerRepository, PartIsFromCategoryRepository partIsFromCategoryRepository, PartIsAppropriateForCarRepository partIsAppropriateForCarRepository, WarehousemanRepository warehousemanRepository, PartIsInStockInWarehouseRepository partIsInStockInWarehouseRepository, DeliverymanRepository deliverymanRepository, CategoryRepository categoryRepository, PartRepository partRepository, WarehouseRepository warehouseRepository, CarRepository carRepository, PartManufacturerRepository partManufacturerRepository, PriceService priceService, RepairShopRepository repairShopRepository, RsForCmRepository rsForCmRepository) {
        this.userService = userService;
        this.carManufacturerRepository = carManufacturerRepository;
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
        this.repairShopRepository = repairShopRepository;
        this.rsForCmRepository = rsForCmRepository;
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
    @GetMapping("/addCarManufacturer")
    public String getCarManView(Model model){
        model.addAttribute("bodyContent","addCarManufacturer");
        return "master-template";
    }
    @PostMapping("/addCarManufacturer")
    public void saveCarManufacturer(@RequestParam String name,@RequestParam String location,
                                    Model model, HttpServletResponse response) {
        carManufacturerRepository.save(new CarManufacturer(name,location));
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/addPartManufacturer")
    public String getPartManView(Model model){
        model.addAttribute("bodyContent","addPartManufacturer");
        return "master-template";
    }
    @PostMapping("/addPartManufacturer")
    public void savePartManufacturer(@RequestParam String name,@RequestParam String location,
                                    Model model, HttpServletResponse response) {
        partManufacturerRepository.save(new PartManufacturer(name,location));
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/addCategory")
    public String getCategoryView(Model model){
        model.addAttribute("bodyContent","addCategory");
        return "master-template";
    }
    @PostMapping("/addCategory")
    public void saveCategory(@RequestParam String name,
                                    Model model, HttpServletResponse response) {
        categoryRepository.save(new Category(name));
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/addCar")
    public String getCarView(Model model){
        model.addAttribute("bodyContent","addCar");
        model.addAttribute("manufacturers",carManufacturerRepository.findAll());
        return "master-template";
    }
    @PostMapping("/addCar")
    public void saveCar(@RequestParam Integer since,@RequestParam Integer till,
                             @RequestParam String name,@RequestParam Integer mId,
                                HttpServletResponse response) {
        carRepository.save(new Car(since,till,name,carManufacturerRepository.findById(mId).get()));
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/addRepairShop")
    public String getRepairShopView(Model model){
        model.addAttribute("bodyContent","addRepairShop");
        model.addAttribute("manufacturers",carManufacturerRepository.findAll());
        return "master-template";
    }
    @PostMapping("/addRepairShop")
    public void saveRepairShop(@RequestParam String name,@RequestParam String location,
                        @RequestParam String number,@RequestParam Integer carMId,
                        HttpServletResponse response) {
        RepairShop newRs = new RepairShop(name,location,number,
                List.of(carManufacturerRepository.findById(carMId).get()));
        repairShopRepository.save(newRs);
        rsForCmRepository.save(new RsForCm(newRs.getId(), carMId));
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/addWarehouse")
    public String getWarehouseView(Model model){
        model.addAttribute("bodyContent","addWarehouse");
        return "master-template";
    }
    @PostMapping("/addWarehouse")
    public void saveWarehouse(@RequestParam String name,
                        HttpServletResponse response) {
        warehouseRepository.save(new Warehouse(name));
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
