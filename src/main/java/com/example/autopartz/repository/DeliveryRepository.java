package com.example.autopartz.repository;

import com.example.autopartz.model.Delivery;
import com.example.autopartz.model.Deliveryman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Integer> {
    List<Delivery> findAllByDeliveryman(Deliveryman deliveryman);
}
