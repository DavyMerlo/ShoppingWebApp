package com.davy.restapi.orderlines.dto;

import com.davy.restapi.product.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderLineDetail {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("product")
    private ProductDTO product;

    @JsonProperty("quantity")
    private short quantity;

    @JsonProperty("totalPrice")
    private float totalPrice;

    public OrderLineDetail(Long id, ProductDTO product, short quantity, float totalPrice) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
