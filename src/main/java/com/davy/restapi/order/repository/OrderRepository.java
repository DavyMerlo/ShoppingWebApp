package com.davy.restapi.order.repository;

import com.davy.restapi.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>,
        CustomOrderRepository{

    Optional<Order> findTopByUserIdOrderByCreatedAtDesc(Long userId);
}
