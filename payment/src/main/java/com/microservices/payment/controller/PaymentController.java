package com.microservices.payment.controller;


import com.microservices.payment.dto.PayReq;
import com.microservices.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public void pay(@RequestParam(name = "id") Long id, @RequestBody PayReq payment){
        paymentService.checkExistsAndPay(id, payment);
    }
}
