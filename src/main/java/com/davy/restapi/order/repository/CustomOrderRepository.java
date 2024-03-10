package com.davy.restapi.order.repository;

import com.davy.restapi.order.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface CustomOrderRepository {

    Optional<OrderEntity> getOrderByUserId(Long id);

    List<OrderEntity> getOrdersByUserId(Long userId);
}
