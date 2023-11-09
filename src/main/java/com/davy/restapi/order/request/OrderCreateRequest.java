package com.davy.restapi.order.request;

import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.orderlines.dto.OrderLineItems;
import com.davy.restapi.payment.enums.PaymentMethod;
import com.davy.restapi.payment.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public class OrderCreateRequest {
    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("status")
    private OrderStatus status;

    @JsonProperty("paymentMethod")
    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;

    @JsonProperty("paymentMethod")
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

    @JsonProperty("orderLines")
    @Enumerated(EnumType.ORDINAL)
    private List<OrderLineItems> orderLines;
}
