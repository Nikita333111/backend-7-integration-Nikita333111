package com.microservices.order.messages;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderProcessingConfig {
    public static final String ORDER_DIRECT_EXCHANGE = "order-direct-exchange";
    public static final String ORDER_CREATE_QUEUE = "create-queue";
    public static final String ROUTING_KEY_ORDER_CREATE = "create";

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(ORDER_DIRECT_EXCHANGE);
    }

    @Bean
    public Queue orderCreateQueue(){
        return new Queue(ORDER_CREATE_QUEUE, false);
    }

    @Bean
    public Binding orderBinding(){
        return BindingBuilder.bind(orderCreateQueue()).to(directExchange()).with(ROUTING_KEY_ORDER_CREATE);
    }
}
