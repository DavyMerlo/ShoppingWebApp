package com.davy.restapi.payment.response;

import com.davy.restapi.payment.dto.PaymentItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentResponse {

    @JsonProperty("payment")
    public PaymentItems payment;
}
