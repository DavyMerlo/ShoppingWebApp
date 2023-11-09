package com.davy.restapi.orderlines.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderLineItems {

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("quantity")
    private short quantity;
}
