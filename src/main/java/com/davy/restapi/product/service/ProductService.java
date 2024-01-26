package com.davy.restapi.product.service;

import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductResponse;

import java.util.Map;

public interface ProductService{
    Map<String, Object> findAllProductsPageable(int page);
    Map<String, Object> filterAndSearchProductsByNamePageable(Long categoryId,
                                                              Long subCategoryId,
                                                              String name,
                                                              int page,
                                                              String sortBy,
                                                              String sortOrder);
    ProductResponse findProductById(Long id);
    ProductResponse saveProduct(ProductRequest request);
    ProductResponse updateProductById(Long id,
                             ProductRequest request);
}
