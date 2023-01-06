package com.example.autopartz.repository;

import com.example.autopartz.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair,Integer> {
    List<Repair> findAllById(Integer id);
}
