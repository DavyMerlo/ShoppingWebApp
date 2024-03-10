package com.davy.restapi.order.dto;

import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.orderlines.dto.OrderLineDetail;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDetailDTO(
        Long id,
        OrderStatus status,
        LocalDateTime date,
        List<OrderLineDetail> orderLines
) {
}