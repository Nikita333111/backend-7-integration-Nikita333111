package com.microservices.mailing.messages;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailingConfig {

    public static final String ORDER_SUCCESS_CREATION_NOTIFICATION_QUEUE = "success-created-queue";
    public static final String PAYMENT_NOTIFICATION_QUEUE = "payment-notification";

    @Bean
    public Queue successCreationNotificationQueue(){
        return new Queue(ORDER_SUCCESS_CREATION_NOTIFICATION_QUEUE,false);
    }

    @Bean
    public Queue successPaymentNotificationQueue(){
        return new Queue(PAYMENT_NOTIFICATION_QUEUE, false);
    }
}
