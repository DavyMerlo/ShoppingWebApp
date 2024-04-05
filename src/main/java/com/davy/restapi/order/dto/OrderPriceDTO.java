package com.davy.restapi.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderPriceDTO (
        @JsonProperty("orderId") Long orderId,
        @JsonProperty("vatValue") BigDecimal vatValue,
        @JsonProperty("totalPrice") BigDecimal totalPrice
){
}
