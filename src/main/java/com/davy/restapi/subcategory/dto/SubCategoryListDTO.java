package com.davy.restapi.subcategory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class SubCategoryListDTO {

    private List<Object> subCategories;

    public SubCategoryListDTO(List<Object> subCategories) {
        this.subCategories = subCategories;
    }
}
