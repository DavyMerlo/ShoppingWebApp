package com.davy.restapi.category.mapper;

import com.davy.restapi.category.dto.CategoryTryDetailsDTO;
import com.davy.restapi.shared.utils.JSONResponseToObject;
import com.davy.restapi.subcategory.dto.SubCategoryDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JSONResponseToCategory implements JSONResponseToObject<CategoryTryDetailsDTO> {

    @Override
    public CategoryTryDetailsDTO mapJSONResponseToObject(JSONObject response)
            throws JSONException {
        var result = response.getJSONObject("result");
        var category = result.getJSONObject("category");
        var categoryId = category.getLong("id");
        var categoryName = category.getString("name");
        var subCategoriesList = category.getJSONArray("subCategories");
        var subCategories = new ArrayList<SubCategoryDto>();

        for (int i = 0; i < subCategoriesList.length(); i++) {
            var subCategoryJson = subCategoriesList.getJSONObject(i);
            var subCategoryId = subCategoryJson.getLong("id");
            var subCategoryName = subCategoryJson.getString("name");

            subCategories.add(new SubCategoryDto(subCategoryId, subCategoryName));
        }
        return new CategoryTryDetailsDTO(
                categoryId,
                categoryName,
                subCategories);
    }
}
