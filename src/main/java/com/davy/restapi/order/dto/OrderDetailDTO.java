package com.davy.restapi.order.dto;

import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.orderlines.dto.OrderLineDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDetailDTO(
        @JsonProperty Long id,
        @JsonProperty OrderStatus status,
        @JsonProperty LocalDateTime date,
        @JsonProperty List<OrderLineDetail> orderLines
) {
}