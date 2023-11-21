package com.davy.restapi.product.repository;

import com.davy.restapi.product.dto.ProductDetails;
import com.davy.restapi.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>,
        CustomProductRepository{
}
