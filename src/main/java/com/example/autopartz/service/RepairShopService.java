package com.example.autopartz.service;

import com.example.autopartz.model.RepairShop;

import java.util.List;

public interface RepairShopService {
    List<RepairShop> findAll();
    RepairShop getByName(String name);
    RepairShop getById(Integer id);
}
