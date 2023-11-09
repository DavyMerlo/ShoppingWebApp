package com.davy.restapi.payment.response;

import com.davy.restapi.payment.dto.PaymentItems;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PaymentListResponse {

    @JsonProperty("payments")
    public List<PaymentItems> payments;
    {
        payments = new ArrayList<>();
    }
}
