package com.example.autopartz.repository;

import com.example.autopartz.model.RepairShopReviewsSummary;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// извештај за сите сервиси и за сите број на reviews и avg рејтинг
@Repository
@Transactional
public interface RepairShopReviewSummaryRepository extends JpaRepository<RepairShopReviewsSummary,Long> {
    List<RepairShopReviewsSummary> findAllByRsid(Long rsid);
}
