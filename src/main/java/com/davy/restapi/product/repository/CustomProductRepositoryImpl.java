package com.davy.restapi.product.repository;

import com.davy.restapi.product.entity.ProductEntity;
import com.davy.restapi.shared.repository.CrudRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CustomProductRepositoryImpl extends CrudRepositoryImpl<ProductEntity>
        implements CustomProductRepository  {

    @PersistenceContext
    private final EntityManager entityManager;

    public CustomProductRepositoryImpl(EntityManager entityManager) {
        super(entityManager, ProductEntity.class);
        this.entityManager = entityManager;
    }
}
