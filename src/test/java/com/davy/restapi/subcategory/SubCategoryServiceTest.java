package com.davy.restapi.subcategory;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.category.dto.CategoryRequestDTO;
import com.davy.restapi.category.entity.Category;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.service.CrudService;
import com.davy.restapi.subcategory.dto.SubCategoryDetailDTO;
import com.davy.restapi.subcategory.dto.SubCategoryDto;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import com.davy.restapi.subcategory.entity.SubCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

public class SubCategoryServiceTest extends TestContainer {

    @Autowired
    private CrudService<SubCategory, SubCategoryRequestDTO> subCategoryService;

    @DisplayName("Get all subcategories")
    @Test
    @Order(1)
    void shouldGetAllSubCategories(){
        List<SubCategoryDto> subCategories = (List<SubCategoryDto>) subCategoryService.findAll();
        assertThat(subCategories).hasSize(56);
    }

    @DisplayName("Get sub category by id")
    @Test
    @Order(2)
    void shouldGetSubCategoryById(){
        SubCategoryDetailDTO subCategory = (SubCategoryDetailDTO) subCategoryService.findById(35L);
        assertThat(subCategory.getId()).isEqualTo(35L);
        assertThat(subCategory.getName()).isEqualTo("Perfume");
        assertThat(subCategory.getCategory().id()).isEqualTo(7L);
        assertThat(subCategory.getCategory().name()).isEqualTo("Beautiful & Healthy");
    }

    @DisplayName("Save sub category")
    @Test
    @Order(3)
    void shouldSaveSubCategory(){
        long categoryId = 5L;
        Optional<SubCategoryRequestDTO> request = Optional.of(new SubCategoryRequestDTO(
                "Test Sub Category Saved",
                categoryId
        ));
        subCategoryService.save(request.get());
        SubCategoryDetailDTO savedSubCategory = (SubCategoryDetailDTO) subCategoryService.findById(57L);
        assertNotNull(savedSubCategory);
        assertEquals(57L, savedSubCategory.getId());
        assertEquals("Test Sub Category Saved", savedSubCategory.getName());
        assertEquals(5L, savedSubCategory.getCategory().id());
        assertEquals("Clothing, Shoes and Accessories", savedSubCategory.getCategory().name());
    }

    @DisplayName("Update sub category")
    @Test
    @Order(4)
    void shouldUpdateSubCategory(){
        long categoryId = 5L;
        Optional<SubCategoryRequestDTO> request = Optional.of(new SubCategoryRequestDTO(
                "Test Sub Category Updated",
                categoryId
        ));
        subCategoryService.updateById(35L, request.get());
        SubCategoryDetailDTO updatedSubCategory = (SubCategoryDetailDTO) subCategoryService.findById(35L);
        assertNotNull(updatedSubCategory);
        assertEquals(35L, updatedSubCategory.getId());
        assertEquals("Test Sub Category Updated", updatedSubCategory.getName());
        assertEquals(5L, updatedSubCategory.getCategory().id());
        assertEquals("Clothing, Shoes and Accessories", updatedSubCategory.getCategory().name());
    }
}
