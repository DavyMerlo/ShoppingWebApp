package com.davy.restapi.subcategory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SubCategoryDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public SubCategoryDto(Long id,
                       String name) {
        this.id = id;
        this.name = name;
    }
}
