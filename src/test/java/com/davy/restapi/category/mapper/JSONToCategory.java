package com.davy.restapi.category.mapper;

import com.davy.restapi.category.dto.CategoryTryDetailsDTO;
import com.davy.restapi.shared.utils.JSONToObject;
import com.davy.restapi.subcategory.dto.SubCategoryDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JSONToCategory implements JSONToObject<CategoryTryDetailsDTO> {

    @Override
    public CategoryTryDetailsDTO mapJSONResponseToObject(JSONObject response)
            throws JSONException {
        var result = response.getJSONObject("result");
        var category = result.getJSONObject("category");
        var categoryId = category.getLong("id");
        var categoryName = category.getString("name");
        var subCategoryList = category.getJSONArray("subCategories");
        var subCategories = new ArrayList<SubCategoryDTO>();

        for (int i = 0; i < subCategoryList.length(); i++) {
            var subCategoryJson = subCategoryList.getJSONObject(i);
            var subCategoryId = subCategoryJson.getLong("id");
            var subCategoryName = subCategoryJson.getString("name");

            subCategories.add(new SubCategoryDTO(subCategoryId, subCategoryName));
        }
        return new CategoryTryDetailsDTO(
                categoryId,
                categoryName,
                subCategories);
    }
}
