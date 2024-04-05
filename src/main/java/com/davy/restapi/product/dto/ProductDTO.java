package com.davy.restapi.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProductDTO(
        @JsonProperty("id")Long id,
        @JsonProperty("name")String name,
        @JsonProperty("sellingPrice") BigDecimal sellingPrice
){}
