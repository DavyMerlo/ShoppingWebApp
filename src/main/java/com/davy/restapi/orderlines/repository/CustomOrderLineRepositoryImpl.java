package com.davy.restapi.orderlines.repository;

import com.davy.restapi.orderlines.entity.OrderLine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomOrderLineRepositoryImpl implements CustomOrderLineRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<OrderLine> getAllOrderlines() {
        Query query = entityManager.createQuery("SELECT ol FROM OrderLine ol");
        return query.getResultList();
    }

    @Override
    public Optional<OrderLine> getOrderLineById(Long id) {
        return Optional.ofNullable(entityManager.find(OrderLine.class, id));
    }

    @Override
    public void saveOrderLine(OrderLine orderLine) {
        entityManager.persist(orderLine);
    }

    @Override
    public void updateOrderLine(OrderLine orderLine) {
        entityManager.persist(orderLine);
    }

    @Override
    public void deleteOrderLine(OrderLine orderLine) {

    }
}
