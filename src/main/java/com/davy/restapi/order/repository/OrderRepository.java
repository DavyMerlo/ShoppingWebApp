package com.davy.restapi.order.repository;

import com.davy.restapi.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>,
        CustomOrderRepository{

//    Optional<OrderEntity> findTopByUserIdOrderByCreatedAtDesc(Long userId);
}
