package com.microservices.order.service.impl;

import com.microservices.order.dto.OrderReq;
import com.microservices.order.messages.OrderProducer;
import com.microservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderProducer orderProducer;

    @Override
    public void createOrder(OrderReq order) {
        orderProducer.processOrderCreation(order);
    }
}




















