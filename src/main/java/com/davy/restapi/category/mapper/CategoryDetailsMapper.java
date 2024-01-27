package com.davy.restapi.category.mapper;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.category.dto.CategoryDetails;
import com.davy.restapi.subcategory.mapper.SubCategoryMapper;
import com.davy.restapi.subcategory.entity.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryDetailsMapper implements Function<Category, CategoryDetails> {

    private final SubCategoryMapper subCategoryMapper;

    @Override
    public CategoryDetails apply(Category category) {
        return new CategoryDetails(
                category.getId(),
                category.getName(),
                subCategoryMapper.apply(new SubCategory())
        );
    }
}
