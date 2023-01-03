package com.example.autopartz.service;

import com.example.autopartz.model.Part;

import java.util.List;

public interface PartService {
    List<Part> findAll();
    Part findById(Long id);
}
