package com.davy.restapi.product.service;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.subcategory.entity.SubCategory;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecification {

    private ProductSpecification(){}

    public static Specification<Product> nameLike(String nameLike) {
        return (root, query, builder) ->
                builder.like(builder.upper(root.get("name")), "%" + nameLike.toUpperCase() + "%");
    }

    public static Specification<Product> byCategoryAndSubCategory(Long catId, Long subCatId) {
        return (root, query, builder) -> {
            Join<Product, Category> categoryJoin = root.join("category");
            Join<Product, SubCategory> subCategoryJoin = root.join("subCategory");

            Predicate catPredicate = builder.equal(categoryJoin.get("id"), catId);
            Predicate subCatPredicate = builder.equal(subCategoryJoin.get("id"), subCatId);

            return builder.and(catPredicate, subCatPredicate);
        };
    }

    public static Specification<Product> byCategory(Long catId) {
        return (root, query, builder) -> {
            Join<Product, Category> categoryJoin = root.join("category");
            return builder.equal(categoryJoin.get("id"), catId);
        };
    }

    public static Specification<Product> bySubCategory(Long subCatId) {
        return (root, query, builder) -> {
            Join<Product, SubCategory> subCategoryJoin = root.join("subCategory");
            return builder.equal(subCategoryJoin.get("id"), subCatId);
        };
    }
}
