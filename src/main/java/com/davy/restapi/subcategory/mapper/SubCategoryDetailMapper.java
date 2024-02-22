package com.davy.restapi.subcategory.mapper;

import com.davy.restapi.category.mapper.CategoryMapper;
import com.davy.restapi.subcategory.dto.SubCategoryDetailDTO;
import com.davy.restapi.subcategory.entity.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SubCategoryDetailMapper implements Function<SubCategory, SubCategoryDetailDTO> {

    private final CategoryMapper categoryMapper;

    @Override
    public SubCategoryDetailDTO apply(SubCategory subCategory) {
        return new SubCategoryDetailDTO(
                subCategory.getId(),
                subCategory.getName(),
                categoryMapper.apply(subCategory.getCategory())
        );
    }
}
