package com.davy.restapi.category;

import com.davy.restapi.category.entity.CategoryEntity;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.repository.CrudRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryRepositoryTest extends TestContainer {

    @Autowired
    private CrudRepository<CategoryEntity> categoryRepository;

    @DisplayName("Get all categories")
    @Test
    @Order(1)
    void shouldGetAllCategories(){
        var categories = categoryRepository.getAll();
        assertThat(categories).hasSize(10);
    }

    @DisplayName("Get category by id")
    @Test
    @Order(2)
    void shouldGetCategoryById(){
        var category = categoryRepository.getById(2L);
        assertThat(category.get().getId()).isEqualTo(2L);
        assertThat(category.get().getName()).isEqualTo("Music, Movies & Games");
        assertThat(category.get().getSubcategories().size()).isEqualTo(7);
    }

    @DisplayName("Save category")
    @Test
    @Order(3)
    void shouldSaveCategory(){
        var emptySubCatList = new ArrayList<>();
        var category = CategoryEntity.builder()
                .name("Test Category")
                .subcategories(null)
                .build();

        categoryRepository.save(category);
        var savedCategory = categoryRepository.getById(11L);
        assertNotNull(savedCategory);
        assertEquals(11L, savedCategory.get().getId());
        assertEquals("Test Category", savedCategory.get().getName());
        assertEquals(emptySubCatList, savedCategory.get().getSubcategories());
    }

    @DisplayName("Update category")
    @Test
    @Order(4)
    void shouldUpdateCategory(){
        var category = java.util.Optional.ofNullable(CategoryEntity.builder()
                .id(2L)
                .name("Test Category Updated")
                .build());
        categoryRepository.update(category.get());
        var updatedCategory = categoryRepository.getById(2L);
        assertNotNull(updatedCategory);
        assertEquals(2L, updatedCategory.get().getId());
        assertEquals("Test Category Updated", updatedCategory.get().getName());
        assertThat(updatedCategory.get().getSubcategories().size()).isEqualTo(7);
    }
}
