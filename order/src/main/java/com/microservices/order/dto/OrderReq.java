package com.microservices.order.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderReq {
    private String email;
    private Double amount;
}
