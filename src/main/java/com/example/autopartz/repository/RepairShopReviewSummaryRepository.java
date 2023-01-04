package com.example.autopartz.repository;

import com.example.autopartz.model.views.RepairShopReviewsSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

// извештај за сите сервиси и за сите број на reviews и avg рејтинг
@Repository
@Transactional
public interface RepairShopReviewSummaryRepository extends JpaRepository<RepairShopReviewsSummary,Integer> {
    // List<RepairShopReviewsSummary> findAllByRsid(Integer rsid);
}
