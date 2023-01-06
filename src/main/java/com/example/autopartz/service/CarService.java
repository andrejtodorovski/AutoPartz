package com.example.autopartz.service;

import com.example.autopartz.model.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    Car findById(Integer id);
}
