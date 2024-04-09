package com.davy.restapi.product.dto;

import com.davy.restapi.product.enums.Vat;

import java.math.BigDecimal;

public record ProductInvoiceDTO(
        Long id,
        String name,
        Vat vat,
        BigDecimal unitPrice
) {
}
