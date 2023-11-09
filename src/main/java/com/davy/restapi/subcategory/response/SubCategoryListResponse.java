package com.davy.restapi.subcategory.response;

import com.davy.restapi.product.dto.ProductDetails;
import com.davy.restapi.subcategory.dto.SubCategoryItems;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryListResponse {
    @JsonProperty("subcategories")
    public List<SubCategoryItems> subCategories;
    {
        subCategories = new ArrayList<>();
    }
}
