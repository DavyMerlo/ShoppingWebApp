package com.davy.restapi.subcategory.data;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.shared.utils.ExpectedDataProvider;
import com.davy.restapi.subcategory.dto.SubCategoryDetailDTO;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryData implements ExpectedDataProvider<SubCategoryDetailDTO> {

    @Override
    public SubCategoryDetailDTO getObject() {
        return new SubCategoryDetailDTO(
                35L,
                "Perfume",
                new CategoryDTO(
                        7L,
                        "Beautiful & Healthy"
                )
        );
    }

    @Override
    public SubCategoryDetailDTO getSavedObject(){
        return new SubCategoryDetailDTO(
                57L,
                "Test Sub Category Saved",
                new CategoryDTO(
                        7L,
                        "Beautiful & Healthy"
                )
        );
    }

    @Override
    public SubCategoryDetailDTO getUpdatedObject() {
        return new SubCategoryDetailDTO(
                35L,
                "Test Sub Category Updated",
                new CategoryDTO(
                        2L,
                        "Music, Movies & Games"
                )
        );
    }
}
