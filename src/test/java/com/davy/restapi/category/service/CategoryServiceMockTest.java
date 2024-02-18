package com.davy.restapi.category.service;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.category.repository.CategoryRepository;
import com.davy.restapi.category.request.CategoryCreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceMockTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void shouldSaveCategory(){

        CategoryCreateRequest request = new CategoryCreateRequest("TestCategory", null);
        Category savedCategory = Category.builder()
                .id(1L)
                .name(request.getName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .createdBy(2L)
                .updatedBy(2L)
                .subcategories(null)
                .deletedAt(null)
                .build();

        when(categoryRepository.saveCategory(any(Category.class))).thenReturn(savedCategory.getId());
        Long categoryId = categoryService.saveCategory(request);
        verify(categoryRepository, times(1)).saveCategory(any(Category.class));
        assertEquals(savedCategory.getId(), categoryId);
    }
}
