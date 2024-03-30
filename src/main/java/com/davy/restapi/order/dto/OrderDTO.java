package com.davy.restapi.order.dto;

import com.davy.restapi.order.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderDTO(
        Long id,
        Long userId,
        OrderStatus status,
        LocalDateTime date
) {
}
