package com.davy.restapi.payment.mapper;

import com.davy.restapi.payment.dto.PaymentItems;
import com.davy.restapi.payment.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PaymentItemsMapper implements Function<Payment, PaymentItems> {

    @Override
    public PaymentItems apply(Payment payment) {
        return new PaymentItems(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus()
        );
    }
}
