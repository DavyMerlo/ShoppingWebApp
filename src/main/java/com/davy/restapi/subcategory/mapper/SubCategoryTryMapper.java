package com.davy.restapi.subcategory.mapper;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.subcategory.dto.SubCategoryDetailDTO;
import com.davy.restapi.subcategory.dto.SubCategoryDto;
import com.davy.restapi.subcategory.entity.SubCategory;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubCategoryTryMapper implements ObjectMapper<SubCategoryRequestDTO, SubCategory> {

    private final CrudRepository<com.davy.restapi.category.entity.Category> categoryRepository;

    @Override
    public SubCategory mapSourceToDestination(SubCategoryRequestDTO source,
                                              SubCategory destination) {
        var category = categoryRepository
                .getById(source.getCategoryId())
                .get();

        destination.setName(source.getName());
        destination.setCategory(category);
        return destination;
    }

    @Override
    public SubCategoryDto mapToDto(SubCategory subCategory) {
        return new SubCategoryDto(
                subCategory.getId(),
                subCategory.getName()
        );
    }

    @Override
    public SubCategoryDetailDTO mapToDetailsDto(SubCategory subCategory) {

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
    public SubCategory mapToEntity(SubCategoryRequestDTO request) {
        var category = categoryRepository
                .getById(request.getCategoryId())
                .get();

        return SubCategory.builder()
                .name(request.getName())
                .category(category)
                .build();
    }

    @Override
    public Object mapToListDto(SubCategory entity) {
        return null;
    }
}
