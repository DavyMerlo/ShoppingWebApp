package com.davy.restapi.orderlines.dto;

import com.davy.restapi.product.dto.ProductDetails;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderLineDetails {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("product")
    private ProductDetails productDetails;


}
