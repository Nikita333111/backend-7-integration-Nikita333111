package com.microservices.order.service;


import com.microservices.order.dto.OrderReq;

public interface OrderService {
    void createOrder(OrderReq order);
}
