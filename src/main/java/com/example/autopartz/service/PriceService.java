package com.example.autopartz.service;

import com.example.autopartz.model.Part;
import com.example.autopartz.model.Price;

import java.util.List;

public interface PriceService {
    List<Price> findPriceForPart(Part part);
}
