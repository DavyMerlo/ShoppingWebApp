package com.davy.restapi.orderlines.dto;

import com.davy.restapi.product.dto.ProductInvoiceDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderLineInvoiceDTO (
        @JsonProperty("id") Long id,
        @JsonProperty("product") ProductInvoiceDTO product,
        @JsonProperty("quantity") short quantity,
        @JsonProperty("totalPrice") BigDecimal totalPrice
) {
}
