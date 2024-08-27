package com.microservices.mailing.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.mailing.dto.OrderResponse;
import com.microservices.mailing.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailingReceiver {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = MailingConfig.ORDER_SUCCESS_CREATION_NOTIFICATION_QUEUE)
    public void successCreationListener(String message){
        try {
            OrderResponse orderResponse = objectMapper.readValue(message, OrderResponse.class);
            String emailTo = orderResponse.getEmail();
            String text = "Your successfully created order: " + orderResponse;
            emailService.sendEmailMessage(emailTo,"Order creation", text);

            System.out.println("Send email about successful creation to: " + emailTo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = MailingConfig.PAYMENT_NOTIFICATION_QUEUE)
    public void orderPaidNotification(String message){
        try {
            OrderResponse orderResponse = objectMapper.readValue(message, OrderResponse.class);
            String emailText = "Your order with id " + orderResponse.getId() + " isPaid=" + orderResponse.getPaid();
            emailService.sendEmailMessage(orderResponse.getEmail(),"Order payment", emailText);
            System.out.println("notify " + orderResponse.getEmail() + " about successful payment");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
