package com.davy.restapi.category.mapper;

import com.davy.restapi.category.dto.CategoryDetails;
import com.davy.restapi.category.dto.CategoryUpdate;
import com.davy.restapi.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryUpdateMapper implements Function<Category, CategoryUpdate> {

    @Override
    public CategoryUpdate apply(Category category) {
        return new CategoryUpdate(
                category.getId(),
                category.getName()
        );
    }
}
