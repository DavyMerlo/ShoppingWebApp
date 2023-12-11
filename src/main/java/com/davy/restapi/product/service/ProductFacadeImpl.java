package com.davy.restapi.product.service;

import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Primary
public class ProductFacadeImpl implements ProductFacade{

    private final ProductService productService;

    @Override
    public Map<String, Object> findAllProductsPageable(int page) {
        return productService.findAllProductsPageable(page);
    }

    @Override
    public Map<String, Object> filterAndSearchProductsByNamePageable(Long categoryId, Long subCategoryId, String name, int page) {
        return productService.filterAndSearchProductsByNamePageable(categoryId, subCategoryId, name,page);
    }

    @Override
    public ProductResponse findProductById(Long id) {
        return productService.findProductById(id);
    }

    @Override
    public Map<String, Object> saveProduct(ProductRequest request) {
        return productService.saveProduct(request);
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest request) {
        return productService.updateProductById(id, request);
    }
}
