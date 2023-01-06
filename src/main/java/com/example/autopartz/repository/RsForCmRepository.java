package com.example.autopartz.repository;

import com.example.autopartz.model.manytomany.RsForCm;
import com.example.autopartz.model.manytomany.RsForCmId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RsForCmRepository extends JpaRepository<RsForCm, RsForCmId> {
    List<RsForCm> findAllByCmid(Integer cmid);
}
