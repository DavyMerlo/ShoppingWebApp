package com.davy.restapi.category.repository;

import com.davy.restapi.category.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CustomCategoryRepository {

    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    Optional<Category> saveCategory(Category category);
    void updateCategory(Category category);
    void removeCategory(Category category);
    Optional<Category> getCategoryBySubCategoryId(Long subCategoryId);

}
