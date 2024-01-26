package com.davy.restapi.category.dto;

import com.davy.restapi.subcategory.dto.SubCategoryItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CategorySubCatList {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("subCategories")
    private List<SubCategoryItems> subCategories;

    public CategorySubCatList(Long id,
                           String name,
                           List<SubCategoryItems> subCategories) {
        this.id = id;
        this.name = name;
        this.subCategories = subCategories;
    }
}
