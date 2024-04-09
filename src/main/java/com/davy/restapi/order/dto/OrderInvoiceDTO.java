package com.davy.restapi.order.dto;

import com.davy.restapi.orderlines.dto.OrderLineInvoiceDTO;
import com.davy.restapi.product.enums.Vat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderInvoiceDTO (
        @JsonProperty("id") Long id,
        @JsonProperty("date") LocalDateTime date,
        @JsonProperty("orderlines") List<OrderLineInvoiceDTO> orderLines,
        @JsonProperty("vat") Vat vat,
        @JsonProperty("netTotal") BigDecimal netTotal,
        @JsonProperty("vatTotal") BigDecimal vatTotal,
        @JsonProperty("grossTotal") BigDecimal grossTotal
) {
}
