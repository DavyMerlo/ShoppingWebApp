package com.davy.restapi.category.service;

import com.davy.restapi.category.request.CategoryCreateRequest;
import com.davy.restapi.category.request.CategoryUpdateRequest;
import com.davy.restapi.category.response.CategoryListResponse;
import com.davy.restapi.category.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class CategoryFacadeImpl implements CategoryFacade{

    private final CategoryService categoryService;

    @Override
    public CategoryListResponse findAllCategories() {
        return categoryService.findAllCategories();
    }

    @Override
    public CategoryResponse findCategoryById(Long id) {
        return categoryService.findCategoryById(id);
    }

    @Override
    public CategoryResponse findCategoryBySubCategoryId(Long subCatId) {
        return categoryService.findCategoryBySubCategoryId(subCatId);
    }

    @Override
    public CategoryResponse updateCategoryById(Long id, CategoryUpdateRequest request) {
        return categoryService.updateCategoryById(id, request);
    }

    @Override
    public CategoryListResponse saveCategory(CategoryCreateRequest request) {
        return categoryService.saveCategory(request);
    }
}
