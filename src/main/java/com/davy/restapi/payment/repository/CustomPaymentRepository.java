package com.davy.restapi.payment.repository;

import com.davy.restapi.payment.entity.PaymentEntity;

import java.util.List;
import java.util.Optional;

public interface CustomPaymentRepository {

    List<PaymentEntity> getAllPayments();
    Optional<PaymentEntity> getPaymentById(Long id);
    Optional<PaymentEntity> savePayment(PaymentEntity payment);
    void updatePayment(PaymentEntity payment);
    void removePayment(PaymentEntity payment);
}
