package com.davy.restapi.product.service;

import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductListResponse;
import com.davy.restapi.product.response.ProductResponse;

import java.util.Map;

public interface ProductService{
    ProductListResponse findAllProducts();
    ProductResponse findProductById(Long id);

    ProductListResponse saveProduct(ProductRequest request);

    ProductResponse updateProductById(Long id,
                             ProductRequest request);

    Map<String, Object> findAllProductsPageable(int page, int size);
}
