package com.davy.restapi.product.service;

import com.davy.restapi.category.entity.CategoryEntity;
import com.davy.restapi.product.entity.ProductEntity;
import com.davy.restapi.subcategory.entity.SubCategoryEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecification {

    private ProductSpecification(){}

    public static Specification<ProductEntity> nameLike(String nameLike) {
        return (root, query, builder) ->
                builder.like(builder.upper(root.get("name")), "%" + nameLike.toUpperCase() + "%");
    }

    public static Specification<ProductEntity> byCategoryAndSubCategory(Long catId, Long subCatId) {
        return (root, query, builder) -> {
            Join<ProductEntity, CategoryEntity> categoryJoin = root.join("category");
            Join<ProductEntity, SubCategoryEntity> subCategoryJoin = root.join("subCategory");

            Predicate catPredicate = builder.equal(categoryJoin.get("id"), catId);
            Predicate subCatPredicate = builder.equal(subCategoryJoin.get("id"), subCatId);

            return builder.and(catPredicate, subCatPredicate);
        };
    }

    public static Specification<ProductEntity> byCategory(Long catId) {
        return (root, query, builder) -> {
            Join<ProductEntity, CategoryEntity> categoryJoin = root.join("category");
            return builder.equal(categoryJoin.get("id"), catId);
        };
    }

    public static Specification<ProductEntity> bySubCategory(Long subCatId) {
        return (root, query, builder) -> {
            Join<ProductEntity, SubCategoryEntity> subCategoryJoin = root.join("subCategory");
            return builder.equal(subCategoryJoin.get("id"), subCatId);
        };
    }
}
