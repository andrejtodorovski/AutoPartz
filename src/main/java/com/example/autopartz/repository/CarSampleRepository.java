package com.example.autopartz.repository;

import com.example.autopartz.model.CarSample;
import com.example.autopartz.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarSampleRepository extends JpaRepository<CarSample,Integer> {
    List<CarSample> findAllByClient(Client client);
}
