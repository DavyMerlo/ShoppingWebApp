package com.davy.restapi.category.mapper;

import com.davy.restapi.category.dto.CategoryItems;
import com.davy.restapi.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryItemsMapper implements Function<Category, CategoryItems> {

    @Override
    public CategoryItems apply(Category category) {
        return new CategoryItems(
                category.getId(),
                category.getName()
        );
    }
}
