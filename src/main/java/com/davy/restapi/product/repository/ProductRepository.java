package com.davy.restapi.product.repository;


import com.davy.restapi.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


public interface ProductRepository extends JpaRepository<Product, Long>,
        CustomProductRepository{

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.subCategory.id = :subCategoryId")
    Page<Product> findAllAndFindByCategoryIdAAndSubCategoryId(@Param("categoryId")
                                                              Long categoryId,
                                                              @Param("subCategoryId")
                                                              Long subCategoryId,
                                                              Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    Page<Product> findAllAndFindByCategoryId(@Param("categoryId")
                                             Long categoryId,
                                             Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.subCategory.id = :subCategoryId")
    Page<Product> findAllAndFindBySubCategoryId(@Param("subCategoryId")
                                                Long subCategoryId,
                                                Pageable pageable);

    Page<Product> findAll(@Nullable Specification<Product> spec, @NonNull Pageable pageable);
}
