package com.microservices.order.controller;

import com.microservices.order.dto.OrderReq;
import com.microservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderReq order){
        if((order.getEmail() == null || order.getEmail().isBlank()) || (order.getAmount() == null || order.getAmount() == 0))
            return new ResponseEntity<>("email and amount must be valid", HttpStatus.BAD_REQUEST);

        orderService.createOrder(order);
        return ResponseEntity.ok("Order created !");
    }

}
