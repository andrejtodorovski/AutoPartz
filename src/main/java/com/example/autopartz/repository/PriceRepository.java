package com.example.autopartz.repository;

import com.example.autopartz.model.Part;
import com.example.autopartz.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price,Integer> {
    List<Price> findAllByPart(Part part);
}
