package com.davy.restapi.payment.repository;

import com.davy.restapi.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>,
        CustomPaymentRepository {
}
