package com.davy.restapi.orderlines.repository;

import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.orderlines.entity.OrderLineEntity;
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
    public List<OrderLineEntity> getAllOrderlines() {
        Query query = entityManager.createQuery("SELECT ol FROM OrderLineEntity ol");
        return query.getResultList();
    }

    @Override
    public Optional<OrderLineEntity> getOrderLineById(Long id) {
        return Optional.ofNullable(entityManager.find(OrderLineEntity.class, id));
    }

    @Override
    public Optional<OrderEntity> getOrderByOrderLineId(Long orderLineId) {
        Query query = entityManager.createQuery(
                "SELECT o FROM OrderEntity o JOIN o.orderItems ol WHERE ol.id = :orderLineId");
        query.setParameter("orderLineId", orderLineId);
        OrderEntity result = (OrderEntity) query.getSingleResult();
        return Optional.ofNullable(result);
    }

    @Override
    public void saveOrderLine(OrderLineEntity orderLine) {
        entityManager.persist(orderLine);
    }

    @Override
    public void updateOrderLine(OrderLineEntity orderLine) {
        entityManager.persist(orderLine);
    }

    @Override
    public void deleteOrderLine(OrderLineEntity orderLine) {

    }
}
