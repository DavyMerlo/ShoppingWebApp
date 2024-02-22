package com.davy.restapi.category.mapper;

import com.davy.restapi.category.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryMapper implements Function<com.davy.restapi.category.entity.Category, CategoryDTO> {

    @Override
    public CategoryDTO apply(com.davy.restapi.category.entity.Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }
}
