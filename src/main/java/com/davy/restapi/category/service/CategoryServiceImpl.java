package com.davy.restapi.category.service;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.category.dto.CategoryRequestDTO;
import com.davy.restapi.category.entity.CategoryEntity;
import com.davy.restapi.category.repository.CategoryRepository;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import com.davy.restapi.subcategory.entity.SubCategoryEntity;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends CrudServiceImpl<CategoryEntity, CategoryRequestDTO>
        implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ObjectMapper<SubCategoryRequestDTO, SubCategoryEntity> subCategoryMapper;
    private final ObjectMapper<CategoryRequestDTO, CategoryEntity> categoryMapper;

    public CategoryServiceImpl(CrudRepository<CategoryEntity> repository,
                               ObjectMapper<CategoryRequestDTO, CategoryEntity> categoryMapper,
                               ObjectMapper<SubCategoryRequestDTO, SubCategoryEntity> subCategoryMapper,
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

