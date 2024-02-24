package com.davy.restapi.subcategory;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.subcategory.entity.SubCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SubCategoryRepositoryTest extends TestContainer {

    @Autowired
    private CrudRepository<SubCategory> subCategoryRepository;

    @Autowired
    private CrudRepository<Category> categoryRepository;

    @DisplayName("Get all subcategories")
    @Test
    @Order(1)
    void shouldGetAllSubCategories(){
        var subCategories = subCategoryRepository.getAll();
        assertThat(subCategories).hasSize(56);
    }

    @DisplayName("Get subcategory by id")
    @Test
    @Order(2)
    void shouldGetSubCategoryById(){
        var subCategory = subCategoryRepository.getById(35L);
        assertThat(subCategory.get().getId()).isEqualTo(35L);
        assertThat(subCategory.get().getName()).isEqualTo("Perfume");
        assertThat(subCategory.get().getCategory().getId()).isEqualTo(7L);
        assertThat(subCategory.get().getCategory().getName()).isEqualTo("Beautiful & Healthy");
    }

    @DisplayName("Save subcategory")
    @Test
    @Order(3)
    void shouldSaveCategory(){
        var parentCategory = categoryRepository.getById(5L);
        var subcategory = SubCategory.builder()
                .name("Test SubCategory Saved")
                .category(parentCategory.get())
                .build();
        subCategoryRepository.save(subcategory);
        var savedSubCategory = subCategoryRepository.getById(57L);
        assertNotNull(savedSubCategory);
        assertEquals(57L, savedSubCategory.get().getId());
        assertEquals("Test SubCategory Saved", savedSubCategory.get().getName());
        assertThat(savedSubCategory.get().getCategory().getId()).isEqualTo(5L);
        assertThat(savedSubCategory.get().getCategory().getName()).isEqualTo("Clothing, Shoes and Accessories");
    }

    @DisplayName("Update subcategory")
    @Test
    @Order(4)
    void shouldUpdateSubCategory(){
        var parentCategory = categoryRepository.getById(2L);
        var subCategory = java.util.Optional.ofNullable(SubCategory.builder()
                .id(35L)
                .name("Test SubCategory Updated")
                .category(parentCategory.get())
                .build());
        subCategoryRepository.update(subCategory.get());
        var updatedSubCategory = subCategoryRepository.getById(35L);
        assertNotNull(updatedSubCategory);
        assertEquals(35L, updatedSubCategory.get().getId());
        assertEquals("Test SubCategory Updated", updatedSubCategory.get().getName());
        assertThat(updatedSubCategory.get().getCategory().getId()).isEqualTo(2L);
        assertThat(updatedSubCategory.get().getCategory().getName()).isEqualTo("Music, Movies & Games");
    }
}
