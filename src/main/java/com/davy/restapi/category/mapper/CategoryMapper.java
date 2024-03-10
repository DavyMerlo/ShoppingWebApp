package com.davy.restapi.category.mapper;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.category.dto.CategoryTryDetailsDTO;
import com.davy.restapi.category.dto.CategoryRequestDTO;
import com.davy.restapi.category.entity.CategoryEntity;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.subcategory.dto.SubCategoryDTO;
import com.davy.restapi.subcategory.entity.SubCategoryEntity;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryMapper implements ObjectMapper<CategoryRequestDTO, CategoryEntity> {

    private final ObjectMapper<SubCategoryRequestDTO, SubCategoryEntity> subCategoryMapper;

    @Override
    public CategoryEntity mapSourceToDestination(CategoryRequestDTO source,
                                                 CategoryEntity destination) {
        destination.setName(source.getName());
        return destination;
    }

    @Override
    public CategoryDTO mapToDto(CategoryEntity category) {
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }

    @Override
    public CategoryTryDetailsDTO mapToDetailsDto(CategoryEntity category) {

        List<SubCategoryDTO>subCategories = category.getSubcategories()
                .stream()
                .map(entity -> (SubCategoryDTO) subCategoryMapper.mapToDto(entity))
                .collect(Collectors.toList());

        return new CategoryTryDetailsDTO(
                category.getId(),
                category.getName(),
                subCategories
        );
    }

    @Override
    public CategoryEntity mapToEntity(CategoryRequestDTO request) {
        List<SubCategoryEntity> subCategories = new ArrayList<>();
        return CategoryEntity.builder()
                .name(request.getName())
                .subcategories(subCategories)
                .build();
    }

    @Override
    public Object mapToListDto(CategoryEntity category) {
        return null;
    }
}
