package com.davy.restapi.category.repository;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.shared.repository.CrudRepositoryImpl;
import com.davy.restapi.subcategory.entity.SubCategory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomCategoryRepositoryImpl extends CrudRepositoryImpl<Category>
        implements CustomCategoryRepository{

    private final EntityManager entityManager;

    public CustomCategoryRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Category.class);
        this.entityManager = entityManager;
    }

    @Override
    public List<SubCategory> subCategoriesByCategoryId(Long categoryId) {
        return entityManager.createQuery(
                        "SELECT sub FROM Category cat " +
                        "JOIN cat.subcategories sub " +
                        "WHERE cat.id = :categoryId", SubCategory.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public Optional<Category> categoryBySubCategoryId(Long subCategoryId){
                return Optional.ofNullable((Category) entityManager
                .createQuery
                        ("SELECT C " +
                         "FROM Category C " +
                         "WHERE C.id IN" +
                         "(SELECT category.id " +
                         "FROM SubCategory WHERE id =:subCategoryId)")
                .setParameter("subCategoryId", subCategoryId)
                .getSingleResult());
    }
}
