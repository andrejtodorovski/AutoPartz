package com.example.autopartz.repository;

import com.example.autopartz.model.OrdersForUserId;
import com.example.autopartz.model.RepairsForUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairsForUserRepository extends JpaRepository<RepairsForUser, OrdersForUserId> {
    List<RepairsForUser> findAllByUserid(Long userid);
}
