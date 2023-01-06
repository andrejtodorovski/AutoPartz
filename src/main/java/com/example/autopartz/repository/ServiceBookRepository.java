package com.example.autopartz.repository;

import com.example.autopartz.model.CarSample;
import com.example.autopartz.model.ServiceBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceBookRepository extends JpaRepository<ServiceBook, Integer> {
    ServiceBook findByCarSample(CarSample carSample);
}
