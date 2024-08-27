package com.microservices.payment.service;


import com.microservices.payment.dto.PayReq;

public interface PaymentService {
    void pay(Long id, Boolean orderExists);

    void checkExistsAndPay(Long id, PayReq payment);
}
