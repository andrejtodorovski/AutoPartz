package com.example.autopartz.service.impl;

import com.example.autopartz.model.Repair;
import com.example.autopartz.repository.RepairRepository;
import com.example.autopartz.service.RepairService;
import org.springframework.stereotype.Service;

@Service
public class RepairServiceImpl implements RepairService {
    private final RepairRepository repairRepository;

    public RepairServiceImpl(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    @Override
    public Repair findById(Integer id) {
        return repairRepository.findById(id).get();
    }
}
