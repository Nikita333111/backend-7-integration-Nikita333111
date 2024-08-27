package com.microservices.mailing.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponse {
    private Long id;
    private String email;
    private Double amount;
    private Boolean paid;
}
