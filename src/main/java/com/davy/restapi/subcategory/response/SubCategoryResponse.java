package com.davy.restapi.subcategory.response;

import com.davy.restapi.subcategory.dto.SubCategoryDetailDTO;
import lombok.Data;

@Data
public class SubCategoryResponse {
    public SubCategoryDetailDTO subCategory;
}
