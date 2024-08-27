package com.microservices.payment.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void processCheckOrderExists(Long orderId){
        try {
            String message = objectMapper.writeValueAsString(orderId);
            rabbitTemplate.convertAndSend(
                    PaymentProcessingConfig.ORDER_DIRECT_EXCHANGE,
                    PaymentProcessingConfig.ROUTING_KEY_ORDER_EXISTS_CHECK,
                    message
            );
            System.out.println("send check order exists with id " + orderId + " message from payment service to core");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void processOrderPay(Long id) {
        try {
            String orderId = objectMapper.writeValueAsString(id);
            rabbitTemplate.convertAndSend(
                    PaymentProcessingConfig.ORDER_DIRECT_EXCHANGE,
                    PaymentProcessingConfig.ROUTING_KEY_PAYMENT,
                    orderId
            );
            System.out.println("send pay message from payment service to core");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
