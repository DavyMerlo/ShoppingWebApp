package com.davy.restapi.category.repository;

import com.davy.restapi.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long>,
        CustomCategoryRepository {
}

