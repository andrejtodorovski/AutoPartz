package com.example.autopartz.repository;

import com.example.autopartz.model.manytomany.OrderContainsPart;
import com.example.autopartz.model.manytomany.OrderContainsPartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderContainsPartRepository extends JpaRepository<OrderContainsPart, OrderContainsPartId> {
    List<OrderContainsPart> findAllByOrderid(Integer orderid);
}
