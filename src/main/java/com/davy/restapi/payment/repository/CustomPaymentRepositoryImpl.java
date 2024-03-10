package com.davy.restapi.payment.repository;

import com.davy.restapi.payment.entity.PaymentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomPaymentRepositoryImpl implements CustomPaymentRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PaymentEntity> getAllPayments() {
        Query query = entityManager.createQuery("SELECT P from PaymentEntity P");
        return query.getResultList();
    }

    @Override
    public Optional<PaymentEntity> getPaymentById(Long id) {
        return Optional.ofNullable(entityManager.find(PaymentEntity.class, id));
    }

    @Override
    @Transactional
    public Optional<PaymentEntity> savePayment(PaymentEntity payment) {
        entityManager.persist(payment);
        return Optional.ofNullable(payment);
    }

    @Override
    @Transactional
    public void updatePayment(PaymentEntity payment) {
        entityManager.merge(payment);
    }

    @Override
    public void removePayment(PaymentEntity payment) {

    }
}
