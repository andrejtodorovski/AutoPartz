package com.example.autopartz.repository;

import com.example.autopartz.model.views.PartsForCarTypeAndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PartsForCarTypeAndCategoryRepository extends JpaRepository<PartsForCarTypeAndCategory,Integer> {
    List<PartsForCarTypeAndCategory> findAllByCartypeAndCategory(String cartype, String category);
}
