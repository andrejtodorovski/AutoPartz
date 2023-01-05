package com.example.autopartz.service.impl;

import com.example.autopartz.model.Delivery;
import com.example.autopartz.model.Deliveryman;
import com.example.autopartz.repository.DeliveryRepository;
import com.example.autopartz.service.DeliveryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public List<Delivery> findAllByDeliverer(Deliveryman dm) {
        return deliveryRepository.findAllByDeliveryman(dm);
    }
}
