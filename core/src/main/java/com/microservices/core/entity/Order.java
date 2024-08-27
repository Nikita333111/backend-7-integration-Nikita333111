package com.microservices.core.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "paid")
    private Boolean paid;

    public boolean isPaid(){
        return paid;
    }

    public Order markPaid(){
        paid = true;
        return this;
    }
}
