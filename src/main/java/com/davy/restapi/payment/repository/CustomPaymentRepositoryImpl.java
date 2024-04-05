package com.davy.restapi.payment.repository;

import com.davy.restapi.payment.entity.PaymentEntity;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.repository.CrudRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
