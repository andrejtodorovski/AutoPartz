package com.example.autopartz.service;

import com.example.autopartz.model.Client;
import com.example.autopartz.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order create(Client user);
    Order findById(Integer id);
    void save(Order o);
}

