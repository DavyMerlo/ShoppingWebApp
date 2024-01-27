package com.davy.restapi.subcategory.dto;

import com.davy.restapi.category.dto.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SubCategoryDetail {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("category")
    private Category category;

    public SubCategoryDetail(Long id,
                             String name,
                             Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
}
