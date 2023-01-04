package com.example.autopartz.repository;

import com.example.autopartz.model.views.ReviewsForUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsForUserRepository extends JpaRepository<ReviewsForUser,Integer> {
    List<ReviewsForUser> findAllByUserid(Integer id);
}
