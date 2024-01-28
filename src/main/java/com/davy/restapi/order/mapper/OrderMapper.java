package com.davy.restapi.order.mapper;

import com.davy.restapi.order.dto.Order;
import com.davy.restapi.orderlines.dto.OrderLine;
import com.davy.restapi.orderlines.mapper.OrderLineMapper;
import com.davy.restapi.subcategory.dto.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper implements Function<com.davy.restapi.order.entity.Order, Order> {

    @Override
    public Order apply(com.davy.restapi.order.entity.Order order) {
        return new Order(
                order.getId(),
                order.getStatus(),
                order.getCreatedAt()
        );
    };
};