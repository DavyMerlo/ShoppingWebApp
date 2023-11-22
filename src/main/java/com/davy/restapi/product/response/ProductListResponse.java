package com.davy.restapi.product.response;

import com.davy.restapi.product.dto.ProductDetails;
import com.davy.restapi.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductListResponse {

    @JsonProperty("products")
    public List<ProductDetails> products;
    {
        products = new ArrayList<>();
    }
}
