package com.example.autopartz.repository;

import com.example.autopartz.model.views.DeliveriesInProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveriesInProgressRepository extends JpaRepository<DeliveriesInProgress,Integer> {
    List<DeliveriesInProgress> findAllByUserid(Integer userid);
}
