package com.davy.restapi.subcategory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SubCategoryRequestDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("categoryId")
    private Long categoryId ;

    public SubCategoryRequestDTO(String name, Long categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }
}
