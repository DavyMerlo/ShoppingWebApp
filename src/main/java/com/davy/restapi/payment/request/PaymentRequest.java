package com.davy.restapi.payment.request;

import com.davy.restapi.payment.enums.PaymentMethod;
import com.davy.restapi.payment.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class PaymentRequest {

//    @JsonProperty("orderId")
//    private Long orderId;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("paymentMethod")
    private PaymentMethod paymentMethod;

    @JsonProperty("paymentStatus")
    private PaymentStatus paymentStatus;
}
