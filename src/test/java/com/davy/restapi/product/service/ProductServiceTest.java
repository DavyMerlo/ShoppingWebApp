package com.davy.restapi.product.service;

import com.davy.restapi.product.dto.ProductDetails;
import com.davy.restapi.product.enums.Vat;
import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductListResponse;
import com.davy.restapi.product.response.ProductResponse;
import com.davy.restapi.shared.TestContainer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest extends TestContainer {

    @Autowired
    private ProductService productService;

    @Test
    @Order(2)
    void shouldReturnAListOfProductsResponse() {
        assertThat(productService.findAllProducts(0,20).products)
                .filteredOn("name", "Elon Musk")
                .filteredOn(productDetails -> productDetails.getCategory().getName().equals("Books"))
                .isNotEmpty();
    }

    @Test
    @Order(3)
    void shouldBeAbleToGetAProductById() {
        Long productId = Long.parseLong("1");
        ProductResponse response = productService.findProductById(productId);
        assertThat(response.product.getName()).isEqualTo("Fairy Tale");
    }

    @Test
    @Transactional
    @Order(4)
    void shouldBeAbleToSaveProduct() {
        ProductRequest request = new ProductRequest(
                "Davy",
                "Test",
                250,
                300,
                Vat.STANDARD_RATE,
                (short) 500,
                Long.parseLong("1"),
                Long.parseLong("1"),
                null
        );
        ProductListResponse response = productService.saveProduct(request);
        List<ProductDetails> products = response.products;
        assertThat(products.get(10).getName()).isEqualTo("Davy");
        assertThat(products.get(10).getInventory().id()).isEqualTo(11);
        assertThat(products.get(10).getCategory().getName()).isEqualTo("Books");
    }

    @Test
    @Order(5)
    void shouldBeAbleToUpdateProduct() {
        ProductRequest request = new ProductRequest(
                "Davy",
                "Test",
                250,
                300,
                Vat.STANDARD_RATE,
                (short) 500,
                Long.parseLong("1"),
                Long.parseLong("1"),
                null
        );

        ProductResponse response = productService.updateProductById(Long.parseLong("1"), request);
        assertThat(response.getProduct().getName()).isEqualTo("Davy");
        assertThat(response.getProduct().getDescription()).isEqualTo("Test");
    }
}