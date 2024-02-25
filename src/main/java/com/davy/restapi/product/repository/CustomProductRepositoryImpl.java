package com.davy.restapi.product.repository;

import com.davy.restapi.product.entity.Product;
import com.davy.restapi.shared.repository.CrudRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CustomProductRepositoryImpl extends CrudRepositoryImpl<Product>
        implements CustomProductRepository  {

    @PersistenceContext
    private final EntityManager entityManager;

    public CustomProductRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Product.class);
        this.entityManager = entityManager;
    }
}
