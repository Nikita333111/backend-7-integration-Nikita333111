package com.microservices.order.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.order.dto.OrderReq;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void processOrderCreation(OrderReq order){
        try {
            String orderJson = objectMapper.writeValueAsString(order);
            rabbitTemplate.convertAndSend(
                    OrderProcessingConfig.ORDER_DIRECT_EXCHANGE,
                    OrderProcessingConfig.ROUTING_KEY_ORDER_CREATE,
                    orderJson);
            System.out.println("Successfully send rabbitMQ message to queue 'create-queue'");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}





















