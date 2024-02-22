package com.davy.restapi.category.mapper;

import com.davy.restapi.category.dto.CategorySubCatListDTO;
import com.davy.restapi.category.entity.Category;
import com.davy.restapi.subcategory.dto.SubCategory;
import com.davy.restapi.subcategory.mapper.SubCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorySubCatListMapper implements Function<Category, CategorySubCatListDTO> {

    private final SubCategoryMapper subCategoryMapper;

    @Override
    public CategorySubCatListDTO apply(Category category) {
        List<SubCategory> subCategoryList = convertSubCategories(category.getSubcategories());

        return new CategorySubCatListDTO(
                category.getId(),
                category.getName(),
                subCategoryList
        );
    }

    private List<SubCategory> convertSubCategories(List<com.davy.restapi.subcategory.entity.SubCategory> subCategories) {
        return subCategories.stream()
                .map(subCategoryMapper)
                .collect(Collectors.toList());
    }
}