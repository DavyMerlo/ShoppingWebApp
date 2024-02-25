package com.davy.restapi.product.response;

import com.davy.restapi.product.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductListResponse {
    @JsonProperty("products")
    public List<ProductDTO> productDTOS;
    {
        productDTOS = new ArrayList<>();
    }
}
