package com.davy.restapi.category.service;

import com.davy.restapi.category.request.CategoryCreateRequest;
import com.davy.restapi.category.request.CategoryUpdateRequest;
import com.davy.restapi.category.response.CategoryListResponse;
import com.davy.restapi.category.response.CategorySingleResponse;
import com.davy.restapi.category.response.CategoryUpdateResponse;

public interface CategoryService {

    CategoryListResponse findAllCategories();

    CategorySingleResponse findCategoryById(Long id);

    CategorySingleResponse findCategoryBySubCategoryId(Long subCatId);

    CategorySingleResponse updateCategoryById(Long id, CategoryUpdateRequest request);

    CategoryListResponse saveCategory(CategoryCreateRequest request);
}
