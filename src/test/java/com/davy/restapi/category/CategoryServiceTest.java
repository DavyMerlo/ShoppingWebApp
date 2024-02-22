package com.davy.restapi.category;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.category.dto.CategoryRequestDTO;
import com.davy.restapi.category.dto.CategoryTryDetailsDTO;
import com.davy.restapi.category.entity.Category;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.service.CrudService;
import com.davy.restapi.subcategory.dto.SubCategoryDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryServiceTest extends TestContainer {

    @Autowired
    private CrudService<Category, CategoryRequestDTO> categoryService;

    @Test
    @Order(1)
    void shouldGetAllCategories(){
        List<CategoryDTO> categories = (List<CategoryDTO>) categoryService.findAll();
        assertThat(categories).hasSize(10);
    }

    @Test
    @Order(2)
    void shouldGetCategoryById(){
        CategoryTryDetailsDTO category = (CategoryTryDetailsDTO) categoryService.findById(3L);
        assertThat(category.getId()).isEqualTo(3L);
        assertThat(category.getName()).isEqualTo("Computer & Electronics");

        List<SubCategoryDto> expectedSubCategories = subCategoryList();
        List<SubCategoryDto> actualSubCategories = category.getSubCategories();

        assertThat(actualSubCategories).hasSize(expectedSubCategories.size());

        for (int i = 0; i < expectedSubCategories.size(); i++) {
            assertThat(actualSubCategories.get(i).getId()).isEqualTo(expectedSubCategories.get(i).getId());
            assertThat(actualSubCategories.get(i).getName()).isEqualTo(expectedSubCategories.get(i).getName());
        }
    }

    @Test
    @Order(3)
    @Transactional
    void shouldSaveCategory() throws IOException {
        Optional<CategoryRequestDTO> request = Optional.of(new CategoryRequestDTO(
                "TestCategory Saved",
                null
        ));
        categoryService.save(request.get());
        CategoryTryDetailsDTO savedCategory = (CategoryTryDetailsDTO) categoryService.findById(11L);
        assertNotNull(savedCategory);
        assertEquals(11L, savedCategory.getId());
        assertEquals("TestCategory Saved", savedCategory.getName());
    }

    @Test
    @Order(4)
    void shouldUpdateCategory(){
        Optional<CategoryRequestDTO> request = Optional.of(new CategoryRequestDTO(
                "TestCategory Updated",
                null
        ));
        categoryService.updateById(3L, request.get());
        CategoryTryDetailsDTO updatedCategory = (CategoryTryDetailsDTO) categoryService.findById(3L);
        assertNotNull(updatedCategory);
        assertThat(updatedCategory.getId()).isEqualTo(3L);
        assertThat(updatedCategory.getName()).isEqualTo("TestCategory Updated");

        List<SubCategoryDto> expectedSubCategories = subCategoryList();
        List<SubCategoryDto> actualSubCategories = updatedCategory.getSubCategories();

        assertThat(actualSubCategories).hasSize(expectedSubCategories.size());

        for (int i = 0; i < expectedSubCategories.size(); i++) {
            assertThat(actualSubCategories.get(i).getId()).isEqualTo(expectedSubCategories.get(i).getId());
            assertThat(actualSubCategories.get(i).getName()).isEqualTo(expectedSubCategories.get(i).getName());
        }
    }


    private List<SubCategoryDto> subCategoryList(){
        var subCategories = new ArrayList<SubCategoryDto>();
        subCategories.add(new SubCategoryDto(12L, "Computer & Accessories"));
        subCategories.add(new SubCategoryDto(13L, "Sound and Vision"));
        subCategories.add(new SubCategoryDto(14L, "Photo & Videocameras"));
        subCategories.add(new SubCategoryDto(15L, "Telephony & Tables"));
        subCategories.add(new SubCategoryDto(16L, "Smartwatches & Accessories"));
        subCategories.add(new SubCategoryDto(17L, "Appliances"));
        subCategories.add(new SubCategoryDto(18L, "Kitchen appliances"));
        subCategories.add(new SubCategoryDto(19L, "Personal care devices"));
        return subCategories;
    }
}
