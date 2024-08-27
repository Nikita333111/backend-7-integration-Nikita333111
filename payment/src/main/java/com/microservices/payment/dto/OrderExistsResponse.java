package com.microservices.payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderExistsResponse {
    private Boolean orderExists;
    private Long orderId;
}
