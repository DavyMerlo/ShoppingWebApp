package com.davy.restapi.subcategory.mapper;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.category.entity.CategoryEntity;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.subcategory.dto.SubCategoryDetailDTO;
import com.davy.restapi.subcategory.dto.SubCategoryDTO;
import com.davy.restapi.subcategory.entity.SubCategoryEntity;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubCategoryMapper implements ObjectMapper<SubCategoryRequestDTO, SubCategoryEntity> {

    private final CrudRepository<CategoryEntity> categoryRepository;

    @Override
    public SubCategoryEntity mapSourceToDestination(SubCategoryRequestDTO source,
                                                    SubCategoryEntity destination) {
        var category = categoryRepository
                .getById(source.getCategoryId())
                .get();

        destination.setName(source.getName());
        destination.setCategory(category);
        return destination;
    }

    @Override
    public SubCategoryDTO mapToDto(SubCategoryEntity subCategory) {
        return new SubCategoryDTO(
                subCategory.getId(),
                subCategory.getName()
        );
    }

    @Override
    public SubCategoryDetailDTO mapToDetailsDto(SubCategoryEntity subCategory) {

        if(subCategory.getCategory().getId() == null){
            new CategoryDTO(null, null);
        }

        return new SubCategoryDetailDTO(
                subCategory.getId(),
                subCategory.getName(),
                new CategoryDTO
                        (subCategory.getCategory().getId(),
                        subCategory.getCategory().getName())
        );
    }

    @Override
    public SubCategoryEntity mapToEntity(SubCategoryRequestDTO request) {
        var category = categoryRepository
                .getById(request.getCategoryId())
                .get();

        return SubCategoryEntity.builder()
                .name(request.getName())
                .category(category)
                .build();
    }

    @Override
    public Object mapToListDto(SubCategoryEntity entity) {
        return null;
    }
}
