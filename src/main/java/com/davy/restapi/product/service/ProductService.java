package com.davy.restapi.product.service;

import com.davy.restapi.product.entity.Product;
import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductListResponse;
import com.davy.restapi.product.response.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;

public interface ProductService{
    ProductListResponse findAllProducts(int page,
                                        int size);
    ProductResponse findProductById(Long id);

    ProductListResponse saveProduct(ProductRequest request) throws IOException;

    ProductResponse updateProductById(Long id,
                             ProductRequest request);
}
