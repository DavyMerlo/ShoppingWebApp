package com.davy.restapi.subcategory.repository;

import com.davy.restapi.shared.repository.CrudRepositoryImpl;
import com.davy.restapi.subcategory.entity.SubCategoryEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class CustomSubCategoryRepositoryImpl extends CrudRepositoryImpl<SubCategoryEntity>
    implements CustomSubCategoryRepository{

    public CustomSubCategoryRepositoryImpl(EntityManager entityManager) {
        super(entityManager, SubCategoryEntity.class);
    }
}
