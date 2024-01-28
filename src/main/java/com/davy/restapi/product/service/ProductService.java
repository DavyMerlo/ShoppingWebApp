package com.davy.restapi.product.service;

import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductDetailResponse;
import com.davy.restapi.product.response.ProductListResponse;

import java.util.Map;

public interface ProductService{

    ProductListResponse findAllProducts();
    Map<String, Object> filterAndSearchProductsByNamePageable(Long categoryId,
                                                              Long subCategoryId,
                                                              String name,
                                                              int page,
                                                              String sortBy,
                                                              String sortOrder);
    ProductDetailResponse findProductById(Long id);
    Long saveProduct(ProductRequest request);
    void updateProductById(Long id,
                             ProductRequest request);
}
