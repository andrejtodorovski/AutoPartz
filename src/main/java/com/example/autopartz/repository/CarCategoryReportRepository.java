package com.example.autopartz.repository;

import com.example.autopartz.model.views.CarCategoryReport;
import com.example.autopartz.model.views.CarCategoryReportId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarCategoryReportRepository extends JpaRepository<CarCategoryReport, CarCategoryReportId> {

}
