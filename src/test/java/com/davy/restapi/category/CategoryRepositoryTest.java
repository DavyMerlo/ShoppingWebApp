package com.davy.restapi.category;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.repository.CrudRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryRepositoryTest extends TestContainer {

    @Autowired
    private CrudRepository<Category> categoryRepository;

    @Test
    @Order(1)
    void shouldGetAllCategories(){
        var addresses = categoryRepository.getAll();
        assertThat(addresses).hasSize(10);
    }

    @Test
    @Order(2)
    void shouldGetCategoryById(){
        var address_1 = categoryRepository.getById(2L);
        assertThat(address_1.get().getId()).isEqualTo(2L);
        assertThat(address_1.get().getName()).isEqualTo("Music, Movies & Games");
        assertThat(address_1.get().getSubcategories().size()).isEqualTo(7);
    }

    @Test
    @Order(3)
    void shouldSaveCategory(){
        var emptySubCatList = new ArrayList<>();
        var category = Category.builder()
                .name("TestCategory")
                .subcategories(null)
                .build();

        categoryRepository.save(category);
        var savedCategory = categoryRepository.getById(11L);
        assertNotNull(savedCategory);
        assertEquals(11L, savedCategory.get().getId());
        assertEquals("TestCategory", savedCategory.get().getName());
        assertEquals(emptySubCatList, savedCategory.get().getSubcategories());
    }

    @Test
    @Order(4)
    void shouldUpdateCategory(){
        var category = categoryRepository.getById(1L);

        category = java.util.Optional.ofNullable(Category.builder()
                .id(2L)
                .name("TestCategory Updated")
                .build());

        categoryRepository.update(category.get());
        var updatedCategory = categoryRepository.getById(2L);
        assertNotNull(updatedCategory);
        assertEquals(2L, updatedCategory.get().getId());
        assertEquals("TestCategory Updated", updatedCategory.get().getName());
        assertThat(updatedCategory.get().getSubcategories().size()).isEqualTo(7);
    }
}
