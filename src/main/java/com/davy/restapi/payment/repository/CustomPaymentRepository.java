package com.davy.restapi.payment.repository;

import com.davy.restapi.address.entity.Address;
import com.davy.restapi.payment.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface CustomPaymentRepository {

    List<Payment> getAllPayments();
    Optional<Payment> getPaymentById(Long id);
    Optional<Payment> savePayment(Payment payment);
    void updatePayment(Payment payment);
    void removePayment(Payment payment);
}
