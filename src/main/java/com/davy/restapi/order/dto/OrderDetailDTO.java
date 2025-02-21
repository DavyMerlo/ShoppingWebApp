package com.davy.restapi.order.dto;

import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.orderlines.dto.OrderLineDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderDetailDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("userId") Long userId,
        @JsonProperty("status") OrderStatus status,
        @JsonProperty("date") LocalDateTime date,
        @JsonProperty("orderlines") List<OrderLineDetail> orderLines
) {
}