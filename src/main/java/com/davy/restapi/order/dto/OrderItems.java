package com.davy.restapi.order.dto;

import com.davy.restapi.order.enums.OrderStatus;

public record OrderItems(
        Long id,
        OrderStatus status
) {
}
