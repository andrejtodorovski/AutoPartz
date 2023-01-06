package com.example.autopartz.service.impl;

import com.example.autopartz.model.Client;
import com.example.autopartz.model.Order;
import com.example.autopartz.repository.OrderRepository;
import com.example.autopartz.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order create(Client user) {
        return orderRepository.save(new Order(user));
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void save(Order o) {
        orderRepository.save(o);
    }
}
