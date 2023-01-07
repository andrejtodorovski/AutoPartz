package com.example.autopartz.repository;

import com.example.autopartz.model.views.WarehousemanReport;
import com.example.autopartz.model.views.WarehousemanReportId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehousemanReportRepository extends JpaRepository<WarehousemanReport, WarehousemanReportId> {
    List<WarehousemanReport> findByWid(Integer wid);
}
