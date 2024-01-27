package com.davy.restapi.subcategory.response;

import com.davy.restapi.subcategory.dto.SubCategory;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryListResponse {
    @JsonProperty("subcategories")
    public List<SubCategory> subCategories;
    {
        subCategories = new ArrayList<>();
    }
}
