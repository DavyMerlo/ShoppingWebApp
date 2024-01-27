package com.davy.restapi.subcategory.mapper;


import com.davy.restapi.category.entity.Category;
import com.davy.restapi.category.mapper.CategoryMapper;
import com.davy.restapi.subcategory.dto.SubCategoryDetail;
import com.davy.restapi.subcategory.entity.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SubCategoryDetailMapper implements Function<SubCategory, SubCategoryDetail> {

    private final CategoryMapper categoryMapper;

    @Override
    public SubCategoryDetail apply(SubCategory subCategory) {
        return new SubCategoryDetail(
                subCategory.getId(),
                subCategory.getName(),
                categoryMapper.apply(subCategory.getCategory())
        );
    }
}
