package com.davy.restapi.category.service;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.category.dto.CategoryRequestDTO;
import com.davy.restapi.category.entity.CategoryEntity;
import com.davy.restapi.shared.service.CrudService;

import java.util.List;

public interface CategoryService extends CrudService<CategoryEntity, CategoryRequestDTO> {

    List<Object> subCategoriesByCategoryId(Long categoryId);

    CategoryDTO categoryBySubCategoryId(Long subCatId);
}
