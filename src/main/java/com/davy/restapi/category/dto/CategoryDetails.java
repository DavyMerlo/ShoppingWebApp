package com.davy.restapi.category.dto;

import com.davy.restapi.subcategory.dto.SubCategoryItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryDetails {

    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "sub_category")
    @JsonProperty("subCategory")
    private SubCategoryItems subCategory;

    public CategoryDetails(Long id,
                           String name,
                           SubCategoryItems subCategory) {
        this.id = id;
        this.name = name;
        this.subCategory = subCategory;
    }
}
