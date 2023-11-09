package com.davy.restapi.payment.dto;

import com.davy.restapi.payment.enums.PaymentMethod;
import com.davy.restapi.payment.enums.PaymentStatus;

public record PaymentItems (
        Long id,
        int amount,
        PaymentMethod paymentMethod,
        PaymentStatus paymentStatus
)  {
}
