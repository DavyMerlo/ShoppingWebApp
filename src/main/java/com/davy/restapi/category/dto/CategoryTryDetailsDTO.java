package com.davy.restapi.category.dto;


import com.davy.restapi.subcategory.dto.SubCategoryDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CategoryTryDetailsDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("subCategories")
    private List<SubCategoryDto> subCategories;

    public CategoryTryDetailsDTO(Long id,
                                 String name,
                                 List<SubCategoryDto> subCategories) {
        this.id = id;
        this.name = name;
        this.subCategories = subCategories;
    }
}