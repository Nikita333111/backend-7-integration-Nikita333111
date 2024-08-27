package com.microservices.core.messages;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiverConfig {
    public static final String ORDER_CREATE_QUEUE = "create-queue";
    public static final String ORDER_EXISTS_CHECK_QUEUE = "order-exists-queue";
    public static final String PAYMENT_QUEUE = "payment-queue";

    @Bean
    public Queue orderCreateQueue(){
        return new Queue(ORDER_CREATE_QUEUE, false);
    }

    @Bean
    public Queue checkOrderExistsQueue(){
        return new Queue(ORDER_EXISTS_CHECK_QUEUE, false);
    }

    @Bean
    public Queue paymentQueue(){
        return new Queue(PAYMENT_QUEUE, false);
    }
}
