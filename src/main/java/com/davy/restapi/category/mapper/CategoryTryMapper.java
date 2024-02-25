package com.davy.restapi.category.mapper;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.category.dto.CategoryTryDetailsDTO;
import com.davy.restapi.category.dto.CategoryRequestDTO;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.subcategory.dto.SubCategoryDTO;
import com.davy.restapi.subcategory.entity.SubCategory;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryTryMapper implements ObjectMapper<CategoryRequestDTO, com.davy.restapi.category.entity.Category> {

    private final ObjectMapper<SubCategoryRequestDTO, SubCategory> subCategoryMapper;

    @Override
    public com.davy.restapi.category.entity.Category mapSourceToDestination(CategoryRequestDTO source,
                                                                            com.davy.restapi.category.entity.Category destination) {
        destination.setName(source.getName());
        return destination;
    }

    @Override
    public CategoryDTO mapToDto(com.davy.restapi.category.entity.Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }

    @Override
    public CategoryTryDetailsDTO mapToDetailsDto(com.davy.restapi.category.entity.Category category) {

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
    public com.davy.restapi.category.entity.Category mapToEntity(CategoryRequestDTO request) {
        List<SubCategory> subCategories = new ArrayList<>();
        return com.davy.restapi.category.entity.Category.builder()
                .name(request.getName())
                .subcategories(subCategories)
                .build();
    }

    @Override
    public Object mapToListDto(com.davy.restapi.category.entity.Category category) {
        return null;
    }
}
