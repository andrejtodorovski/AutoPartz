package com.example.autopartz.repository;

import com.example.autopartz.model.views.OrdersForUser;
import com.example.autopartz.model.views.OrdersForUserId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface OrdersForUserRepository extends JpaRepository<OrdersForUser, OrdersForUserId> {
    List<OrdersForUser> findAllByUserid(Long userid);
}
