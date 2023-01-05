package com.example.autopartz.service;

import com.example.autopartz.model.Delivery;
import com.example.autopartz.model.Deliveryman;

import java.util.List;

public interface DeliveryService {
    List<Delivery> findAllByDeliverer(Deliveryman dm);
}
