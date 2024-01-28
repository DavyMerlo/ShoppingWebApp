package com.davy.restapi.subcategory.repository;

import com.davy.restapi.subcategory.entity.SubCategory;

import java.util.List;
import java.util.Optional;

public interface CustomSubCategoryRepository {

    List<SubCategory> getAllSubCategories();
    Optional<SubCategory> getSubCategoryById(Long id);
    Long saveSubCategory(SubCategory subCategory);
    void updateSubCategory(SubCategory subCategory);
    void removeSubCategory(SubCategory subCategory);
}
