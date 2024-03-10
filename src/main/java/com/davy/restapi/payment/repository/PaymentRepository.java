package com.davy.restapi.payment.repository;

import com.davy.restapi.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>,
        CustomPaymentRepository {
}
