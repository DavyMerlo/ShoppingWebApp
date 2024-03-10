package com.davy.restapi.order.repository;

import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.shared.repository.CrudRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomOrderRepositoryImpl extends CrudRepositoryImpl<OrderEntity>
    implements CustomOrderRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public CustomOrderRepositoryImpl(EntityManager entityManager) {
        super(entityManager, OrderEntity.class);
        this.entityManager = entityManager;
    }

    @Override
    public List<OrderEntity> getOrdersByUserId(Long userId) {
        Query query = entityManager.createQuery("SELECT o FROM OrderEntity o WHERE o.user.id = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public Optional<OrderEntity> getOrderByUserId(Long userId) {
        return Optional.ofNullable(entityManager.find(OrderEntity.class, userId));
    }
}
