package com.example.autopartz.repository;

import com.example.autopartz.model.Warehouse;
import com.example.autopartz.model.Warehouseman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehousemanRepository extends JpaRepository<Warehouseman,Integer> {
    List<Warehouseman> findAllByWarehouse(Warehouse warehouse);
}
