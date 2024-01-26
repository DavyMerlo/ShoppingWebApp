package com.davy.restapi.order.repository;

import com.davy.restapi.order.entity.Order;
import com.davy.restapi.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface CustomOrderRepository {

    List<Order> getAllOrders();

    Optional<Order> getOrderById(Long id);

    Optional<Order> saveOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(Order order);
}
