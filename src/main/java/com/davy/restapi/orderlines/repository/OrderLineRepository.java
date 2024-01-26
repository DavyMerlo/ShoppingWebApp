package com.davy.restapi.orderlines.repository;

import com.davy.restapi.orderlines.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long>,
        CustomOrderLineRepository{
}
