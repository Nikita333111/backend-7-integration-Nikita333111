package com.microservices.core.entity;

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
