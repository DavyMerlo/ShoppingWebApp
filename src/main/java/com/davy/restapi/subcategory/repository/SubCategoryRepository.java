package com.davy.restapi.subcategory.repository;

import com.davy.restapi.subcategory.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>,
        CustomSubCategoryRepository {
}
