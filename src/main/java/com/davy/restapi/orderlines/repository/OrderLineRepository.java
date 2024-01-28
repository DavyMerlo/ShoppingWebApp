package com.davy.restapi.orderlines.repository;

import com.davy.restapi.orderlines.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long>,
        CustomOrderLineRepository{

    List<OrderLine> findOrderLinesByOrderId(Long orderId);
}
