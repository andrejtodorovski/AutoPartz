package com.example.autopartz.repository;

import com.example.autopartz.model.manytomany.PartIsInStockInWarehouse;
import com.example.autopartz.model.manytomany.PartIsInStockInWarehouseId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartIsInStockInWarehouseRepository extends JpaRepository<PartIsInStockInWarehouse, PartIsInStockInWarehouseId> {
    List<PartIsInStockInWarehouse> findAllByWarehouseid(Integer warehouseid);
}
