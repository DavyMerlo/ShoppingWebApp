package com.davy.restapi.category.mapper;

import com.davy.restapi.category.dto.CategorySubCatList;
import com.davy.restapi.category.entity.Category;
import com.davy.restapi.subcategory.dto.SubCategoryItems;
import com.davy.restapi.subcategory.entity.SubCategory;
import com.davy.restapi.subcategory.mapper.SubCategoryItemsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorySubCatListMapper implements Function<Category, CategorySubCatList> {

    private final SubCategoryItemsMapper subCategoryItemsMapper;

    @Override
    public CategorySubCatList apply(Category category) {
        List<SubCategoryItems> subCategoryItemsList = convertSubCategories(category.getSubcategories());

        return new CategorySubCatList(
                category.getId(),
                category.getName(),
                subCategoryItemsList
        );
    }

    private List<SubCategoryItems> convertSubCategories(List<SubCategory> subCategories) {
        return subCategories.stream()
                .map(subCategoryItemsMapper)
                .collect(Collectors.toList());
    }
}