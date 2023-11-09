package com.davy.restapi.subcategory.repository;

import com.davy.restapi.subcategory.entity.SubCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomSubCategoryRepositoryImpl implements CustomSubCategoryRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<SubCategory> getAllSubCategories() {
        Query query = entityManager.createQuery("SELECT SC FROM SubCategory SC");
        return query.getResultList();
    }

    @Override
    public Optional<SubCategory> getSubCategoryById(Long id) {
        return Optional.ofNullable(entityManager.find(SubCategory.class, id));
    }

    @Override
    @Transactional
    public Optional<SubCategory> saveSubCategory(SubCategory subCategory) {
        entityManager.persist(subCategory);
        return Optional.ofNullable(subCategory);
    }

    @Override
    @Transactional
    public void updateSubCategory(SubCategory subCategory) {
        entityManager.merge(subCategory);
    }

    @Override
    public void removeSubCategory(SubCategory subCategory) {

    }
}
