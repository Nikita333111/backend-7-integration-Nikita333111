package com.microservices.core.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.core.entity.Order;
import com.microservices.core.entity.OrderExistsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void sendSuccessPaymentNotification(Order order) {
        try {
            String orderJson = objectMapper.writeValueAsString(order);
            rabbitTemplate.convertAndSend(
                    ProducerConfig.ORDER_DIRECT_EXCHANGE,
                    ProducerConfig.ROUTING_KEY_PAYMENT_NOTIFICATION,
                    orderJson
                    );
            System.out.println("Send rabbitMQ message from core service to mail about email notification need");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendSuccessCreationNotification(Order order){
        try {
            String orderJson = objectMapper.writeValueAsString(order);
            rabbitTemplate.convertAndSend(
                    ProducerConfig.ORDER_DIRECT_EXCHANGE,
                    ProducerConfig.ROUTING_KEY_ORDER_SUCCESS_CREATION_NOTIFICATION,
                    orderJson
            );
            System.out.println("Send rabbitMQ message from core to mailing service about successful creation");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendOrderExistsResponse(Boolean orderExists, Long orderId) throws JsonProcessingException {
        OrderExistsResponse orderExistsResponse = new OrderExistsResponse(orderExists, orderId);
        String jsonOrderExistsResponse = objectMapper.writeValueAsString(orderExistsResponse);
        rabbitTemplate.convertAndSend(
                ProducerConfig.ORDER_DIRECT_EXCHANGE,
                ProducerConfig.ROUTING_KEY_ORDER_EXISTS_RESPONSE,
                jsonOrderExistsResponse
        );
        System.out.println("send info about order existence with id: " + orderId + " from core service back to payment");
    }
}
