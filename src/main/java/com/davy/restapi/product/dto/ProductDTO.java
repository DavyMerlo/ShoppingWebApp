package com.davy.restapi.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDTO(
        @JsonProperty("id")Long id,
        @JsonProperty("name")String name,
        @JsonProperty("sellingPrice") float sellingPrice
){}
