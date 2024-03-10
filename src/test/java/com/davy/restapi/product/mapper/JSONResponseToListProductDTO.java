package com.davy.restapi.product.mapper;

import com.davy.restapi.product.dto.ProductDTO;
import com.davy.restapi.shared.utils.JSONResponseToObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JSONResponseToListProductDTO implements JSONResponseToObject<List<ProductDTO>> {

    @Override
    public List<ProductDTO> mapJSONResponseToObject(JSONObject response) throws JSONException {
        var result = response.getJSONObject("result");
        var productList = result.getJSONArray("products");
        var products = new ArrayList<ProductDTO>();

        for(int i = 0; i < productList.length(); i++){
            var productJson = productList.getJSONObject(i);
            var productId = productJson.getLong("id");
            var productName = productJson.getString("name");
            products.add(new ProductDTO(productId, productName));
        }
        return products;
    }
}
