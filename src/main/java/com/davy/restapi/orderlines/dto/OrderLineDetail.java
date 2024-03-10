package com.davy.restapi.orderlines.dto;

import com.davy.restapi.product.dto.ProductDTO;
import lombok.Data;

@Data
public class OrderLineDetail {

    private Long id;

    private ProductDTO product;

    private short quantity;

    public OrderLineDetail(Long id, ProductDTO product, short quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }
}
