package com.example.autopartz.service;

import com.example.autopartz.model.Delivery;
import com.example.autopartz.model.Deliveryman;
import com.example.autopartz.model.Order;

import java.util.List;

public interface DeliveryService {
    List<Delivery> findAllByDeliverer(Deliveryman dm);
    Delivery findById(Integer id);
    void update(Delivery d);
    Delivery findByOrder(Order o);
}
