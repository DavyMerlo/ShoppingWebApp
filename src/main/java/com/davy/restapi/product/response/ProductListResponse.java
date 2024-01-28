package com.davy.restapi.product.response;

import com.davy.restapi.product.dto.Product;
import com.davy.restapi.product.dto.ProductDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductListResponse {
    @JsonProperty("products")
    public List<Product> products;
    {
        products = new ArrayList<>();
    }
}
