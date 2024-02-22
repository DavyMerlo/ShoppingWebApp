package com.davy.restapi.category.repository;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.subcategory.entity.SubCategory;

import java.util.List;
import java.util.Optional;


public interface CustomCategoryRepository {

    List<SubCategory> subCategoriesByCategoryId(Long categoryId);

    Optional<Category> categoryBySubCategoryId(Long subCategoryId);
}
