package com.davy.restapi.subcategory.repository;

import com.davy.restapi.subcategory.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>,
        CustomSubCategoryRepository {
    List<SubCategory> findByCategoryId(Long id);
}
