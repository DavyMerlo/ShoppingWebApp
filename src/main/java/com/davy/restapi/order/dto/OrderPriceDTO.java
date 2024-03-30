package com.davy.restapi.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Builder
public record OrderPriceDTO (
        @JsonProperty("orderId") Long orderId,
        @JsonProperty("totalPrice") float totalPrice
){
}
