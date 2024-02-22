package com.davy.restapi.subcategory.repository;

import com.davy.restapi.shared.repository.CrudRepositoryImpl;
import com.davy.restapi.subcategory.entity.SubCategory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class CustomSubCategoryRepositoryImpl extends CrudRepositoryImpl<SubCategory>
    implements CustomSubCategoryRepository{

    public CustomSubCategoryRepositoryImpl(EntityManager entityManager) {
        super(entityManager, SubCategory.class);
    }
}
