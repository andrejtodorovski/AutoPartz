package com.example.autopartz.service.impl;

import com.example.autopartz.model.Car;
import com.example.autopartz.repository.CarRepository;
import com.example.autopartz.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
