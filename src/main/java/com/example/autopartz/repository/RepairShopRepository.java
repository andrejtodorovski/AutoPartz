package com.example.autopartz.repository;

import com.example.autopartz.model.RepairShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairShopRepository extends JpaRepository<RepairShop,Integer> {
    RepairShop getRepairShopByName(String name);
}
