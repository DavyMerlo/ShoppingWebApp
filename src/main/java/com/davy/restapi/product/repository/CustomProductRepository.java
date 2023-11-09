package com.davy.restapi.product.repository;

import com.davy.restapi.product.entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Transient;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CustomProductRepository {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);

}
