package com.davy.restapi.payment.request;

import com.davy.restapi.payment.enums.PaymentMethod;
import com.davy.restapi.payment.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaymentCreateRequest {

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("paymentMethod")
    private PaymentMethod paymentMethod;

    @JsonProperty("paymentStatus")
    private PaymentStatus paymentStatus;
}
