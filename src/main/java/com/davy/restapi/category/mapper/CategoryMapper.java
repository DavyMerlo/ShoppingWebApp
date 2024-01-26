package com.davy.restapi.category.mapper;

import com.davy.restapi.category.dto.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryMapper implements Function<com.davy.restapi.category.entity.Category, Category> {

    @Override
    public Category apply(com.davy.restapi.category.entity.Category category) {
        return new Category(
                category.getId(),
                category.getName()
        );
    }
}
