package com.davy.restapi.payment.mapper;

import com.davy.restapi.payment.dto.PaymentItems;
import com.davy.restapi.payment.entity.PaymentEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PaymentItemsMapper implements Function<PaymentEntity, PaymentItems> {

    @Override
    public PaymentItems apply(PaymentEntity payment) {
        return new PaymentItems(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus()
        );
    }
}
