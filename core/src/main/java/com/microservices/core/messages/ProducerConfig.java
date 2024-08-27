package com.microservices.core.messages;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {
    public static final String ORDER_DIRECT_EXCHANGE = "order-direct-exchange";
    public static final String ORDER_SUCCESS_CREATION_NOTIFICATION_QUEUE = "success-created-queue";
    public static final String ROUTING_KEY_ORDER_SUCCESS_CREATION_NOTIFICATION = "success";

    public static final String ORDER_EXISTS_RESPONSE_QUEUE = "order-exists-response-queue";
    public static final String ROUTING_KEY_ORDER_EXISTS_RESPONSE = "exists.response";

    public static final String PAYMENT_NOTIFICATION_QUEUE = "payment-notification";
    public static final String ROUTING_KEY_PAYMENT_NOTIFICATION = "payment.notification";

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(ORDER_DIRECT_EXCHANGE);
    }

    @Bean
    public Queue successCreationNotificationQueue(){
        return new Queue(ORDER_SUCCESS_CREATION_NOTIFICATION_QUEUE,false);
    }

    @Bean
    public Binding successCreationNotificationBindings(){
        return BindingBuilder.bind(successCreationNotificationQueue()).to(directExchange()).with(ROUTING_KEY_ORDER_SUCCESS_CREATION_NOTIFICATION);
    }


    @Bean
    public Queue existsResponseQueue(){
        return new Queue(ORDER_EXISTS_RESPONSE_QUEUE, false);
    }
    @Bean
    public Binding existsResponseBind(){
        return BindingBuilder.bind(existsResponseQueue()).to(directExchange()).with(ROUTING_KEY_ORDER_EXISTS_RESPONSE);
    }

    @Bean
    public Queue paymentNotificationQueue(){
        return new Queue(PAYMENT_NOTIFICATION_QUEUE, false);
    }
    @Bean
    public Binding paymentNotificationBind(){
        return BindingBuilder.bind(paymentNotificationQueue()).to(directExchange()).with(ROUTING_KEY_PAYMENT_NOTIFICATION);
    }
}
