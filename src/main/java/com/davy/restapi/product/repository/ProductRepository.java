package com.davy.restapi.product.repository;


import com.davy.restapi.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,
        CustomProductRepository{

    Page<Product> findAll(@Nullable Specification<Product> spec, @NonNull Pageable pageable);
}
