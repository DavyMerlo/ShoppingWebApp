package com.davy.restapi.category.service;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.category.dto.CategoryRequestDTO;
import com.davy.restapi.category.entity.Category;
import com.davy.restapi.category.repository.CategoryRepository;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import com.davy.restapi.subcategory.entity.SubCategory;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends CrudServiceImpl<Category, CategoryRequestDTO>
        implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ObjectMapper<SubCategoryRequestDTO, SubCategory> subCategoryMapper;
    private final ObjectMapper<CategoryRequestDTO, Category> categoryMapper;

    public CategoryServiceImpl(CrudRepository<Category> repository,
                               ObjectMapper<CategoryRequestDTO, Category> categoryMapper,
                               ObjectMapper<SubCategoryRequestDTO, SubCategory> subCategoryMapper,
                               CategoryRepository categoryRepository) {
        super(repository, categoryMapper);
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.subCategoryMapper = subCategoryMapper;
    }

    @Override
    public List<Object> subCategoriesByCategoryId(Long categoryId) {
        if (categoryRepository.subCategoriesByCategoryId(categoryId).isEmpty()) {
            ThrowException.objectByIdException(categoryId, "Subcategories");
        }
        return categoryRepository.subCategoriesByCategoryId(categoryId)
                .stream()
                .map(subCategoryMapper::mapToDto)
                .toList();
    }

    @Override
    public CategoryDTO categoryBySubCategoryId(Long subCategoryId) {
        if(categoryRepository.categoryBySubCategoryId(subCategoryId).isEmpty()){
            ThrowException.objectByIdException(subCategoryId, "Category");
        }
        return (CategoryDTO) categoryRepository.categoryBySubCategoryId(subCategoryId)
                .stream()
                .map(categoryMapper::mapToDto)
                .findFirst()
                .get();
    }
}

