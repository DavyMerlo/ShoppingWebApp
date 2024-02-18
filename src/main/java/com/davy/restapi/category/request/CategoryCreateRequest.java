package com.davy.restapi.category.request;

import com.davy.restapi.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryCreateRequest {
    @JsonProperty("name")
    private String name;

    @JsonIgnore
    private Product product;

    public CategoryCreateRequest(String name, Product product) {
        this.name = name;
    }
}
