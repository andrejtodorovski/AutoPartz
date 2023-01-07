package com.example.autopartz.repository;

import com.example.autopartz.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    List<Warehouse> findAllByLocation(String location);
    List<Warehouse> findAllById(Integer id);
}
