package com.davy.restapi.category.service;

import com.davy.restapi.category.request.CategoryCreateRequest;
import com.davy.restapi.category.request.CategoryUpdateRequest;
import com.davy.restapi.category.response.CategoryListResponse;
import com.davy.restapi.category.response.CategoryResponse;

public interface CategoryService {

    CategoryListResponse findAllCategories();

    CategoryResponse findCategoryById(Long id);

    CategoryResponse findCategoryBySubCategoryId(Long subCatId);

    void updateCategoryById(Long id, CategoryUpdateRequest request);

    Long saveCategory(CategoryCreateRequest request);
}
