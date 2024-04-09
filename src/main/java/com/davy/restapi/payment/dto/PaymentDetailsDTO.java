package com.davy.restapi.payment.dto;

import com.davy.restapi.payment.enums.PaymentMethod;
import com.davy.restapi.payment.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentDetailsDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("method") PaymentMethod method,
        @JsonProperty("status") PaymentStatus status,
        @JsonProperty("totalPrice") BigDecimal totalPrice,
        @JsonProperty("date") LocalDateTime date
) {
}
