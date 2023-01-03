package com.example.autopartz.repository;

import com.example.autopartz.model.ReviewsForUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsForUserRepository extends JpaRepository<ReviewsForUser,Long> {
    List<ReviewsForUser> findAllByUserid(Long id);
}
