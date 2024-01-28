package com.davy.restapi.product.response;

import com.davy.restapi.product.dto.ProductDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDetailListResponse {

    @JsonProperty("products")
    public List<ProductDetails> products;
    {
        products = new ArrayList<>();
    }
}
