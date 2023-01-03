package com.example.autopartz.repository;

import com.example.autopartz.model.OrdersForUser;
import com.example.autopartz.model.OrdersForUserId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface OrdersForUserRepository extends JpaRepository<OrdersForUser, OrdersForUserId> {
    List<OrdersForUser> findAllByUserid(Long userid);
}
