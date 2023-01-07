package com.example.autopartz.repository;

import com.example.autopartz.model.CarManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarManufacturerRepository extends JpaRepository<CarManufacturer,Integer> {
}
