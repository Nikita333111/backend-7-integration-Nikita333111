package com.microservices.payment.messages;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentProcessingConfig {
    public static final String ORDER_DIRECT_EXCHANGE = "order-direct-exchange";
    public static final String ORDER_EXISTS_CHECK_QUEUE = "order-exists-queue";
    public static final String ROUTING_KEY_ORDER_EXISTS_CHECK = "exists";

    public static final String PAYMENT_QUEUE = "payment-queue";
    public static final String ROUTING_KEY_PAYMENT = "payment";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(ORDER_DIRECT_EXCHANGE);
    }

    @Bean
    public Queue orderExistsCheckQueue(){
        return new Queue(ORDER_EXISTS_CHECK_QUEUE,false);
    }

    @Bean
    public Binding orderExistsBind(){
        return BindingBuilder.bind(orderExistsCheckQueue()).to(directExchange()).with(ROUTING_KEY_ORDER_EXISTS_CHECK);
    }



    @Bean
    public Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE, false);
    }

    @Bean
    public Binding paymentBinding() {
        return BindingBuilder.bind(paymentQueue()).to(directExchange()).with(ROUTING_KEY_PAYMENT);
    }
}