package com.microservices.core.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.core.entity.Order;
import com.microservices.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiverService {
    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;
    private final ProducerService producerService;

    @RabbitListener(queues = ReceiverConfig.ORDER_CREATE_QUEUE)
    public void createOrder(String orderJson){
        try {
            Order order = objectMapper.readValue(orderJson, Order.class);
            order.setPaid(false);
            Order newOrder = orderRepository.save(order);

            System.out.println("Successfully receive creation message from order service in core service");
            System.out.println("New order " + order);

            producerService.sendSuccessCreationNotification(newOrder);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = ReceiverConfig.ORDER_EXISTS_CHECK_QUEUE)
    public void checkOrderExists(String message){
        try {
            Long orderId = objectMapper.readValue(message, Long.class);
            Boolean orderExists = orderRepository.findById(orderId).isPresent();

            producerService.sendOrderExistsResponse(orderExists, orderId);
            System.out.println("order exists: " + orderExists);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = ReceiverConfig.PAYMENT_QUEUE)
    public void paymentProcess(String orderIdMessage){
        try {
            Long orderId = objectMapper.readValue(orderIdMessage, Long.class);
            Order order = orderRepository.findById(orderId).get();

            orderRepository.save(order.markPaid());
            System.out.println("get paid in core service");
            producerService.sendSuccessPaymentNotification(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
















