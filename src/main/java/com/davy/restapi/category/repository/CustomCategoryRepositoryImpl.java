package com.davy.restapi.category.repository;

import com.davy.restapi.category.entity.CategoryEntity;
import com.davy.restapi.shared.repository.CrudRepositoryImpl;
import com.davy.restapi.subcategory.entity.SubCategoryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomCategoryRepositoryImpl extends CrudRepositoryImpl<CategoryEntity>
        implements CustomCategoryRepository{

    @PersistenceContext
    private final EntityManager entityManager;

    public CustomCategoryRepositoryImpl(EntityManager entityManager) {
        super(entityManager, CategoryEntity.class);
        this.entityManager = entityManager;
    }

    @Override
    public List<SubCategoryEntity> subCategoriesByCategoryId(Long categoryId) {
        return entityManager.createQuery(
                        "SELECT sub FROM CategoryEntity cat " +
                        "JOIN cat.subcategories sub " +
                        "WHERE cat.id = :categoryId", SubCategoryEntity.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public Optional<CategoryEntity> categoryBySubCategoryId(Long subCategoryId){
                return Optional.ofNullable((CategoryEntity) entityManager
                .createQuery
                        ("SELECT C " +
                         "FROM CategoryEntity C " +
                         "WHERE C.id IN" +
                         "(SELECT category.id " +
                         "FROM SubCategoryEntity WHERE id =:subCategoryId)")
                .setParameter("subCategoryId", subCategoryId)
                .getSingleResult());
    }
}
