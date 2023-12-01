package com.davy.restapi.product.service;

import com.davy.restapi.product.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecification {

    private ProductSpecification(){}

    public static Specification<Product> nameLike(String nameLike) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + nameLike + "%");
    }
}
