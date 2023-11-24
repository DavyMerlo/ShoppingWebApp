package com.davy.restapi.product.service;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductListResponse;
import com.davy.restapi.product.response.ProductResponse;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Map;

public interface ProductService{
    Map<String, Object> findAllProductsPageable(int page);
    Map<String, Object> findByCategoryIdAndSubCategoryIdPageable(Long categoryId,
                                                                 Long subCategoryId,
                                                                 int page);
    ProductResponse findProductById(Long id);
    Map<String, Object> saveProduct(ProductRequest request);
    ProductResponse updateProductById(Long id,
                             ProductRequest request);
}
