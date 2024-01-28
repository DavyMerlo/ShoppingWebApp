package com.davy.restapi.orderlines.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderLineDetail {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("productId")
    private Long ProductId;

    @JsonProperty("quantity")
    private short quantity;
}
