package com.davy.restapi.category.dto;


import com.davy.restapi.subcategory.dto.SubCategoryDTO;
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
    private List<SubCategoryDTO> subCategories;

    public CategoryTryDetailsDTO(Long id,
                                 String name,
                                 List<SubCategoryDTO> subCategories) {
        this.id = id;
        this.name = name;
        this.subCategories = subCategories;
    }
}