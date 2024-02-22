package com.davy.restapi.category.response;

import com.davy.restapi.category.dto.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryListResponse {

    @JsonProperty("categories")
    public List<CategoryDTO> categories;
    {
        categories = new ArrayList<>();
    }
}
