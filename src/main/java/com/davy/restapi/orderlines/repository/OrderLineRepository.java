package com.davy.restapi.orderlines.repository;

import com.davy.restapi.orderlines.entity.OrderLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLineEntity, Long>,
        CustomOrderLineRepository{

    List<OrderLineEntity> findOrderLinesByOrderId(Long orderId);
}
