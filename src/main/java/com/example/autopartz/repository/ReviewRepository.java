package com.example.autopartz.repository;

import com.example.autopartz.model.Repair;
import com.example.autopartz.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Review findByRepair(Repair repair);
}
