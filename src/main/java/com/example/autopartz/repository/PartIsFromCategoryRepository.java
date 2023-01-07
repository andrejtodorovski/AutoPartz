package com.example.autopartz.repository;

import com.example.autopartz.model.manytomany.PartIsFromCategory;
import com.example.autopartz.model.manytomany.PartIsFromCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartIsFromCategoryRepository extends JpaRepository<PartIsFromCategory, PartIsFromCategoryId> {
}
