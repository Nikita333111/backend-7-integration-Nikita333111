package com.microservices.payment.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.payment.dto.OrderExistsResponse;
import com.microservices.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentReceiver {
    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    @RabbitListener(queues = PaymentReceiverConfig.ORDER_EXISTS_RESPONSE_QUEUE)
    public void orderExistsAndPay(String jsonOrderExistsResponse) {
        try {
            OrderExistsResponse orderExistsResponse = objectMapper.readValue(jsonOrderExistsResponse, OrderExistsResponse.class);

            System.out.println("get message from core service to payment about order existence");
            System.out.println("next step - produce exception if orderExists = false or send pay message otherwise");
            System.out.println(orderExistsResponse);

            paymentService.pay(orderExistsResponse.getOrderId(), orderExistsResponse.getOrderExists());
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

}
