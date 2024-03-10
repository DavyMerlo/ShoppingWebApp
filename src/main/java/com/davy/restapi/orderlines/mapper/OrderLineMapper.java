package com.davy.restapi.orderlines.mapper;

import com.davy.restapi.orderlines.dto.OrderLine;
import com.davy.restapi.orderlines.entity.OrderLineEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OrderLineMapper implements Function<OrderLineEntity, OrderLine> {

    @Override
    public OrderLine apply(OrderLineEntity orderLine) {
        return new OrderLine(
                orderLine.getProduct().getId(),
                (long) orderLine.getQuantity()
        );
    }
}
