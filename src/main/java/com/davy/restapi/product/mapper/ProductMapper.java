package com.davy.restapi.product.mapper;

import com.davy.restapi.product.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class ProductMapper implements Function<Product, com.davy.restapi.product.dto.Product> {

    @Override
    public com.davy.restapi.product.dto.Product apply(Product product) {
        return new com.davy.restapi.product.dto.Product(
                product.getId(),
                product.getName()
        );
    }
}
