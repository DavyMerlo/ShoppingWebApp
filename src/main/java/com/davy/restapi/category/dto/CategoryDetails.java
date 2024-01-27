package com.davy.restapi.category.dto;

import com.davy.restapi.subcategory.dto.SubCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryDetails {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("subCategory")
    private SubCategory subCategory;

    public CategoryDetails(Long id,
                           String name,
                           SubCategory subCategory) {
        this.id = id;
        this.name = name;
        this.subCategory = subCategory;
    }
}
