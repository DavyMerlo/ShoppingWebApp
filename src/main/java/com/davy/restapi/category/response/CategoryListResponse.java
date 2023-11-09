package com.davy.restapi.category.response;

import com.davy.restapi.category.dto.CategoryItems;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CategoryListResponse {

    @JsonProperty("categories")
    public List<CategoryItems> categories;
    {
        categories = new ArrayList<>();
    }
}
