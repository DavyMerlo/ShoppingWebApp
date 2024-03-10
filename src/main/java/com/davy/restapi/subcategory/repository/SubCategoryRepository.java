package com.davy.restapi.subcategory.repository;

import com.davy.restapi.subcategory.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long>,
        CustomSubCategoryRepository {
}
