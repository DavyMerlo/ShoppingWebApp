package com.davy.restapi.category.repository;

import com.davy.restapi.category.entity.CategoryEntity;
import com.davy.restapi.subcategory.entity.SubCategoryEntity;

import java.util.List;
import java.util.Optional;


public interface CustomCategoryRepository {

    List<SubCategoryEntity> subCategoriesByCategoryId(Long categoryId);

    Optional<CategoryEntity> categoryBySubCategoryId(Long subCategoryId);
}
