package com.davy.restapi.payment.repository;

import com.davy.restapi.payment.entity.PaymentEntity;
import com.davy.restapi.shared.repository.CrudRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CustomPaymentRepositoryImpl extends CrudRepositoryImpl<PaymentEntity>
    implements CustomPaymentRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public CustomPaymentRepositoryImpl(EntityManager entityManager) {
        super(entityManager, PaymentEntity.class);
        this.entityManager = entityManager;
    }
}
