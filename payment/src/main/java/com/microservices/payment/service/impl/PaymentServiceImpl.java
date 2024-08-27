package com.microservices.payment.service.impl;

import com.microservices.payment.dto.PayReq;
import com.microservices.payment.exception.OrderNotFoundException;
import com.microservices.payment.messages.PaymentProducer;
import com.microservices.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentProducer paymentProducer;

    public void checkExistsAndPay(Long id, PayReq payment) {
        paymentProducer.processCheckOrderExists(id);
    }

    @Override
    public void pay(Long id, Boolean orderExists) {
        if (orderExists)
            paymentProducer.processOrderPay(id);
        else
            throw new OrderNotFoundException("Order doesnt exists with id " + id);
    }
}



















