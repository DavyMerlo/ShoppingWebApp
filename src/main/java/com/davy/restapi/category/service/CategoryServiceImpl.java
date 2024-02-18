package com.davy.restapi.category.service;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.category.mapper.CategoryMapper;
import com.davy.restapi.category.mapper.CategorySubCatListMapper;
import com.davy.restapi.category.request.CategoryCreateRequest;
import com.davy.restapi.category.request.CategoryUpdateRequest;
import com.davy.restapi.category.response.CategoryListResponse;
import com.davy.restapi.category.response.CategoryResponse;
import com.davy.restapi.category.repository.CategoryRepository;
import com.davy.restapi.shared.exceptions.ThrowException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategorySubCatListMapper categorySubCatListMapper;

    @Override
    public CategoryListResponse findAllCategories() {
        var response = new CategoryListResponse();
        if(categoryRepository.getAllCategories().isEmpty()){
            ThrowException.objectException("Categories");
        }
        response.categories = categoryRepository.getAllCategories()
                .stream()
                .map(categoryMapper)
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public CategoryResponse findCategoryById(Long id) {
        var response = new CategoryResponse();
        if(categoryRepository.getCategoryById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Category");
        }
        response.category= categoryRepository.getCategoryById(id)
                .stream()
                .map(categorySubCatListMapper)
                .findFirst()
                .get();
        return response;
    }

    @Override
    public CategoryResponse findCategoryBySubCategoryId(Long subCatId) {
        var response = new CategoryResponse();
        if(categoryRepository.getCategoryBySubCategoryId(subCatId).isEmpty()){
            ThrowException.objectByIdException(subCatId, "SubCategory");
        }
        response.category = categoryRepository.getCategoryBySubCategoryId(subCatId)
                .stream()
                .map(categorySubCatListMapper)
                .findFirst()
                .get();
        return response;
    }

    @Override
    public void updateCategoryById(Long id, CategoryUpdateRequest request) {
        var category = categoryRepository.getCategoryById(id);
        if(category.isEmpty()){
            ThrowException.objectByIdException(id, "Category");
        }
        category.get().setName(request.getName());
        categoryRepository.updateCategory(category.get());
    }

    @Override
    public Long saveCategory(CategoryCreateRequest request) {
        var category = Category.builder()
                .name(request.getName())
                .build();
        var id = categoryRepository.saveCategory(category);
        return id;
    }
}
