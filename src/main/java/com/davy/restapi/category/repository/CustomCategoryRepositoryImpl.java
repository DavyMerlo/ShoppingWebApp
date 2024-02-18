package com.davy.restapi.category.repository;

import com.davy.restapi.category.entity.Category;
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
public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Category> getAllCategories() {
        Query query = entityManager.createQuery("SELECT c from Category c");
        return query.getResultList();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        Category category = entityManager.find(Category.class, id);
        return Optional.ofNullable(category);
    }

    @Override
    @Transactional
    public Long saveCategory(Category category) {
        entityManager.persist(category);
        entityManager.flush();
        entityManager.refresh(category);
        return category.getId();
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        entityManager.merge(category);
    }

    @Override
    public void removeCategory(Category category) {

    }

    @Override
    public Optional<Category> getCategoryBySubCategoryId(Long subCategoryId) {
        return Optional.ofNullable((Category) entityManager.createQuery
                        ("SELECT C " +
                                "FROM Category C " +
                                "WHERE C.id IN" +
                                "(SELECT category.id " +
                                "FROM SubCategory WHERE id =:subCatId)")
                .setParameter("subCatId", subCategoryId)
                .getSingleResult());
    }
}
