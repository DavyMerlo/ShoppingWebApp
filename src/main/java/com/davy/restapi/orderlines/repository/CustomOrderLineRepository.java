package com.davy.restapi.orderlines.repository;

import com.davy.restapi.order.entity.Order;
import com.davy.restapi.orderlines.entity.OrderLine;

import java.util.List;
import java.util.Optional;

public interface CustomOrderLineRepository {

    List<OrderLine> getAllOrderlines();

    Optional<OrderLine> getOrderLineById(Long id);

    Optional<Order> getOrderByOrderLineId(Long orderLineId);

    void saveOrderLine(OrderLine orderLine);

    void updateOrderLine(OrderLine orderLine);

    void deleteOrderLine(OrderLine orderLine);
}
