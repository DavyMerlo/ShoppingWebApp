package com.davy.restapi.order.mapper;

import com.davy.restapi.order.dto.OrderItems;
import com.davy.restapi.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OrderItemsMapper implements Function<Order, OrderItems> {

    @Override
    public OrderItems apply(Order order) {
        return new OrderItems(
                order.getId(),
                order.getStatus()
        );
    };
};