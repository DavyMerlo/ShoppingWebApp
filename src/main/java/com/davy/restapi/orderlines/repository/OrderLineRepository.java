package com.davy.restapi.orderlines.repository;

import com.davy.restapi.orderlines.entity.OrderLines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLines, Long>,
        CustomOrderLineRepository{
}
