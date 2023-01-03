package com.example.autopartz.service.impl;

import com.example.autopartz.model.Part;
import com.example.autopartz.model.Price;
import com.example.autopartz.repository.PriceRepository;
import com.example.autopartz.service.PriceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> findPriceForPart(Part part) {
        return priceRepository.findAllByPart(part);
    }
}
