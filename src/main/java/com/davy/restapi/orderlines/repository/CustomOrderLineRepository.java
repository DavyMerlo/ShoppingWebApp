package com.davy.restapi.orderlines.repository;

import com.davy.restapi.orderlines.entity.OrderLine;
import com.davy.restapi.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface CustomOrderLineRepository {

    List<OrderLine> getAllOrderlines();

    Optional<OrderLine> getOrderLineById(Long id);

    void saveOrderLine(OrderLine orderLine);

    void updateOrderLine(OrderLine orderLine);

    void deleteOrderLine(OrderLine orderLine);
}
