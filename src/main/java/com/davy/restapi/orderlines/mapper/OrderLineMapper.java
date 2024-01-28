package com.davy.restapi.orderlines.mapper;

import com.davy.restapi.orderlines.dto.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OrderLineMapper implements Function<com.davy.restapi.orderlines.entity.OrderLine, OrderLine> {

    @Override
    public OrderLine apply(com.davy.restapi.orderlines.entity.OrderLine orderLine) {
        return new OrderLine(
                orderLine.getProduct().getId(),
                (long) orderLine.getQuantity()
        );
    }
}
