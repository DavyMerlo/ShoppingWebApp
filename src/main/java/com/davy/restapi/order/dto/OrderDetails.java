package com.davy.restapi.order.dto;

import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.orderlines.dto.OrderLineDetails;
import com.davy.restapi.orderlines.entity.OrderLines;
import com.davy.restapi.payment.entity.Payment;
import com.davy.restapi.user.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class OrderDetails {

    @JsonProperty("id")
    private  Long id;

    @JsonProperty("user")
    public User user;

    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @JsonProperty("orderLines")
    @OneToMany(mappedBy = "order")
    private List<OrderLineDetails> orderItems;

    @JsonProperty("payment")
    private Payment payment;
}
