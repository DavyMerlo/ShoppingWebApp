package com.davy.restapi.subcategory.dto;

import com.davy.restapi.category.dto.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SubCategoryDetailDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("category")
    private CategoryDTO categoryDto;

    public SubCategoryDetailDTO(Long id,
                                String name,
                                CategoryDTO categoryDto) {
        this.id = id;
        this.name = name;
        this.categoryDto = categoryDto;
    }
}
