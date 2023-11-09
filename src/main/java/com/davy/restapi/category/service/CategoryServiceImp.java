package com.davy.restapi.category.service;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.category.mapper.CategoryItemsMapper;
import com.davy.restapi.category.mapper.CategoryUpdateMapper;
import com.davy.restapi.category.request.CategoryCreateRequest;
import com.davy.restapi.category.request.CategoryUpdateRequest;
import com.davy.restapi.category.response.CategoryListResponse;
import com.davy.restapi.category.response.CategorySingleResponse;
import com.davy.restapi.category.repository.CategoryRepository;
import com.davy.restapi.shared.exceptions.ThrowException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryItemsMapper categoryItemsMapper;

    @Override
    public CategoryListResponse findAllCategories() {
        var response = new CategoryListResponse();
        if(categoryRepository.getAllCategories().isEmpty()){
            ThrowException.objectException("Categories");
        }
        response.categories = categoryRepository.getAllCategories()
                .stream()
                .map(categoryItemsMapper)
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public CategorySingleResponse findCategoryById(Long id) {
        var response = new CategorySingleResponse();
        if(categoryRepository.getCategoryById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Category");
        }
        response.category= categoryRepository.getCategoryById(id)
                .stream()
                .map(categoryItemsMapper)
                .findFirst()
                .get();
        return response;
    }

    @Override
    public CategorySingleResponse findCategoryBySubCategoryId(Long subCatId) {
        var response = new CategorySingleResponse();
        if(categoryRepository.getCategoryBySubCategoryId(subCatId).isEmpty()){
            ThrowException.objectByIdException(subCatId, "SubCategory");
        }
        response.category = categoryRepository.getCategoryBySubCategoryId(subCatId)
                .stream()
                .map(categoryItemsMapper)
                .findFirst()
                .get();
        return response;
    }

    @Override
    public CategorySingleResponse updateCategoryById(Long id, CategoryUpdateRequest request) {
        var category = categoryRepository.getCategoryById(id);
        if(category.isEmpty()){
            ThrowException.objectByIdException(id, "Category");
        }
        category.get().setName(request.getName());
        categoryRepository.updateCategory(category.get());
        return this.findCategoryById(category.get().getId());
    }

    @Override
    public CategoryListResponse saveCategory(CategoryCreateRequest request) {
        var category = Category.builder()
                .name(request.getName())
                .build();
        categoryRepository.saveCategory(category);
        return this.findAllCategories();
    }
}
