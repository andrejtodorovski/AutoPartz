package com.example.autopartz.repository;

import com.example.autopartz.model.views.OrdersForUserId;
import com.example.autopartz.model.views.RepairsForUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairsForUserRepository extends JpaRepository<RepairsForUser, Integer> {
    List<RepairsForUser> findAllByUserid(Integer userid);
}
