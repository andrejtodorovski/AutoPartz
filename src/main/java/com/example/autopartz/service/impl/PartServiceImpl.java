package com.example.autopartz.service.impl;

import com.example.autopartz.model.Part;
import com.example.autopartz.repository.PartRepository;
import com.example.autopartz.service.PartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;

    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<Part> findAll() {
        return partRepository.findAll();
    }

    @Override
    public Part findById(Long id) {
        return partRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
