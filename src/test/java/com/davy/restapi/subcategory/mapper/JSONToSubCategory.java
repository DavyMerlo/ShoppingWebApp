package com.davy.restapi.subcategory.mapper;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.shared.utils.JSONToObject;
import com.davy.restapi.subcategory.dto.SubCategoryDetailDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JSONToSubCategory implements JSONToObject<SubCategoryDetailDTO> {

    @Override
    public SubCategoryDetailDTO mapJSONResponseToObject(JSONObject response)
            throws JSONException {
        var result = response.getJSONObject("result");
        var subCategory = result.getJSONObject("subCategory");
        var category = subCategory.getJSONObject("category");
        var actualCategoryObj = new CategoryDTO(
                category.getLong("id"),
                category.getString("name")
        );
        return  new SubCategoryDetailDTO(
                subCategory.getLong("id"),
                subCategory.getString("name"),
                actualCategoryObj
        );
    }
}
