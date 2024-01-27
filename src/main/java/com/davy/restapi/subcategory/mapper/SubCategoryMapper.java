package com.davy.restapi.subcategory.mapper;

import com.davy.restapi.subcategory.dto.SubCategory;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SubCategoryMapper implements Function<com.davy.restapi.subcategory.entity.SubCategory, SubCategory> {

    @Override
    public SubCategory apply(com.davy.restapi.subcategory.entity.SubCategory subCategory) {
        return new SubCategory(
                subCategory.getId(),
                subCategory.getName()
        );
    }
}
