package com.davy.restapi.subcategory.request;

import com.davy.restapi.category.dto.CategoryDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SubCategoryRequest {

    @JsonProperty("name")
    private String name;

    @JsonIgnore
    private CategoryDetails category ;
}
