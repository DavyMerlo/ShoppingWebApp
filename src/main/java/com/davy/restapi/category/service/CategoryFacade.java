package com.davy.restapi.category.service;

import com.davy.restapi.category.request.CategoryCreateRequest;
import com.davy.restapi.category.request.CategoryUpdateRequest;
import com.davy.restapi.category.response.CategoryListResponse;
import com.davy.restapi.category.response.CategoryResponse;

public interface CategoryFacade {
    CategoryListResponse findAllCategories();

    CategoryResponse findCategoryById(Long id);

    CategoryResponse findCategoryBySubCategoryId(Long subCatId);

    CategoryResponse updateCategoryById(Long id, CategoryUpdateRequest request);

    CategoryListResponse saveCategory(CategoryCreateRequest request);
}
