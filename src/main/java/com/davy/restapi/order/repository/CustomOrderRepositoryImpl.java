package com.davy.restapi.order.repository;

import com.davy.restapi.order.entity.Order;
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
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> getAllOrders() {
        Query query = entityManager.createQuery("SELECT o FROM Order o");
        return query.getResultList();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public Optional<Order> getOrderByUserId(Long userId) {
        return Optional.ofNullable(entityManager.find(Order.class, userId));
    }

    @Override
    @Transactional
    public Optional<Order> saveOrder(Order order) {
        entityManager.persist(order);
        return Optional.ofNullable(order);
    }

    @Override
    public void updateOrder(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void deleteOrder(Order order) {

    }
}
