package com.davy.restapi.payment.repository;

import com.davy.restapi.payment.entity.Payment;
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
    public List<Payment> getAllPayments() {
        Query query = entityManager.createQuery("SELECT P from Payment P");
        return query.getResultList();
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        return Optional.ofNullable(entityManager.find(Payment.class, id));
    }

    @Override
    @Transactional
    public Optional<Payment> savePayment(Payment payment) {
        entityManager.persist(payment);
        return Optional.ofNullable(payment);
    }

    @Override
    @Transactional
    public void updatePayment(Payment payment) {
        entityManager.merge(payment);
    }

    @Override
    public void removePayment(Payment payment) {

    }
}
