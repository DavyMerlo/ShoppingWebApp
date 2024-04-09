package com.davy.restapi.payment.dto;

import com.davy.restapi.payment.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentInvoiceDTO (
        @JsonProperty("id") Long id,
        @JsonProperty("method") PaymentMethod method,
        @JsonProperty("date") LocalDateTime date,
        @JsonProperty("grossTotal") BigDecimal grossTotal
) {
}
