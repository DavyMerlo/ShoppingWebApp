package com.davy.restapi.orderlines.repository;

import com.davy.restapi.orderlines.entity.OrderLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLineEntity, Long>,
        CustomOrderLineRepository{

    List<OrderLineEntity> findOrderLinesByOrderId(Long orderId);
}
