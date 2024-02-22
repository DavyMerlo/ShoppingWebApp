package com.davy.restapi.subcategory.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubCategoryListResponse {

    @JsonProperty("subcategories")
    public List<Object> subCategories;
    {
        subCategories = new ArrayList<>();
    }
}
