package com.davy.restapi.orderlines.dto;

import com.davy.restapi.product.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderLineDetail {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("product")
    private ProductDTO product;

    @JsonProperty("quantity")
    private short quantity;

    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

    public OrderLineDetail(Long id,
                           ProductDTO product,
                           short quantity,
                           BigDecimal totalPrice) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
