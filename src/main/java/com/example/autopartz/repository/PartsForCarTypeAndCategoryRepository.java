package com.example.autopartz.repository;

import com.example.autopartz.model.views.PartsForCarTypeAndCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface PartsForCarTypeAndCategoryRepository extends JpaRepository<PartsForCarTypeAndCategory,Long> {
    List<PartsForCarTypeAndCategory> findAllByCartypeAndCategory(String cartype, String category);
}
