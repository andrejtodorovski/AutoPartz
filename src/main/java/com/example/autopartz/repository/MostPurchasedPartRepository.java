package com.example.autopartz.repository;

import com.example.autopartz.model.views.MostPurchasedPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MostPurchasedPartRepository extends JpaRepository<MostPurchasedPart,String> {
}
