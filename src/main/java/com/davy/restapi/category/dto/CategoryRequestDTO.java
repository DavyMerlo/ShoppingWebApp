package com.davy.restapi.category.dto;

import com.davy.restapi.product.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryRequestDTO {
    @JsonProperty("name")
    private String name;

    @JsonIgnore
    private ProductEntity product;

    public CategoryRequestDTO(String name, ProductEntity product) {
        this.name = name;
    }
}
