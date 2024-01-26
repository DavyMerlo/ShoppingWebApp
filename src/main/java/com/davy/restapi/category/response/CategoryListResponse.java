package com.davy.restapi.category.response;

import com.davy.restapi.category.dto.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CategoryListResponse {

    @JsonProperty("categories")
    public List<Category> categories;
    {
        categories = new ArrayList<>();
    }
}
