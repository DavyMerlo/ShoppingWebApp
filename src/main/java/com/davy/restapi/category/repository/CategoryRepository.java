package com.davy.restapi.category.repository;

import com.davy.restapi.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>,
        CustomCategoryRepository {
}

