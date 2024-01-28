package com.davy.restapi.subcategory.service;

import com.davy.restapi.subcategory.request.SubCategoryRequest;
import com.davy.restapi.subcategory.response.SubCategoryListResponse;
import com.davy.restapi.subcategory.response.SubCategoryResponse;

public interface SubCategoryService {
    SubCategoryListResponse findAllSubCategories();

    SubCategoryListResponse findSubCategoriesByCategoryId(Long id);

    SubCategoryResponse findSubCategoryById(Long id);

    void updateSubCategoryById(Long id, SubCategoryRequest request);

    Boolean existsBySubCategoryId(Long id);

    Long saveSubCategory(SubCategoryRequest request);
}
