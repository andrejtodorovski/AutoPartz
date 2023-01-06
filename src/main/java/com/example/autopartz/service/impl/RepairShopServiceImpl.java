package com.example.autopartz.service.impl;

import com.example.autopartz.model.RepairShop;
import com.example.autopartz.repository.RepairShopRepository;
import com.example.autopartz.service.RepairShopService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairShopServiceImpl implements RepairShopService {
    private final RepairShopRepository repairShopRepository;

    public RepairShopServiceImpl(RepairShopRepository repairShopRepository) {
        this.repairShopRepository = repairShopRepository;
    }

    @Override
    public List<RepairShop> findAll() {
        return repairShopRepository.findAll();
    }

    @Override
    public RepairShop getByName(String name) {
        return repairShopRepository.getRepairShopByName(name);
    }

    @Override
    public RepairShop getById(Integer id) {
        return repairShopRepository.findById(id).get();
    }
}
