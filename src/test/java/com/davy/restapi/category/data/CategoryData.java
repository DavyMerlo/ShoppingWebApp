package com.davy.restapi.category.data;

import com.davy.restapi.category.dto.CategoryTryDetailsDTO;
import com.davy.restapi.shared.utils.ExpectedDataProvider;
import com.davy.restapi.subcategory.dto.SubCategoryDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CategoryData implements ExpectedDataProvider<CategoryTryDetailsDTO> {

    @Override
    public CategoryTryDetailsDTO getObject() {
        return new CategoryTryDetailsDTO(
                3L,
                "Computer & Electronics",
                getSubCategories()
        );
    }

    @Override
    public CategoryTryDetailsDTO getSavedObject() {
        return new CategoryTryDetailsDTO(
                11L,
                "Test Category Saved",
                new ArrayList<>()
        );
    }

    @Override
    public CategoryTryDetailsDTO getUpdatedObject() {
        return new CategoryTryDetailsDTO(
                3L,
                "Test Category Updated",
                getSubCategories()
        );
    }

    private List<SubCategoryDto> getSubCategories(){
        return Arrays.asList(
                new SubCategoryDto(12L, "Computer & Accessories"),
                new SubCategoryDto(13L, "Sound and Vision"),
                new SubCategoryDto(14L, "Photo & Videocameras"),
                new SubCategoryDto(15L, "Telephony & Tables"),
                new SubCategoryDto(16L, "Smartwatches & Accessories"),
                new SubCategoryDto(17L, "Appliances"),
                new SubCategoryDto(18L, "Kitchen appliances"),
                new SubCategoryDto(19L, "Personal care devices")
        );
    }
}
