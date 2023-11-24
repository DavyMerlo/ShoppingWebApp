package com.davy.restapi.category.repository;

import com.davy.restapi.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long>,
        CustomCategoryRepository {
    List<Category> findBySubcategoriesId(Long id);
}

