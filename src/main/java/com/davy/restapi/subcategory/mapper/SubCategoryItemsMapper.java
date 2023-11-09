package com.davy.restapi.subcategory.mapper;

import com.davy.restapi.subcategory.dto.SubCategoryItems;
import com.davy.restapi.subcategory.entity.SubCategory;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SubCategoryItemsMapper implements Function<SubCategory, SubCategoryItems> {

    @Override
    public SubCategoryItems apply(SubCategory subCategory) {
        return new SubCategoryItems(
                subCategory.getId(),
                subCategory.getName()
        );
    }
}
