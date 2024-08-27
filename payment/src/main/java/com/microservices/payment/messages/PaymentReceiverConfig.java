package com.microservices.payment.messages;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentReceiverConfig {
    public static final String ORDER_EXISTS_RESPONSE_QUEUE = "order-exists-response-queue";

    @Bean
    public Queue orderExistsResponseQueue(){
        return new Queue(ORDER_EXISTS_RESPONSE_QUEUE, false);
    }
}
