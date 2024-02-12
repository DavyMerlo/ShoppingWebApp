package com.davy.restapi.product.service;

import com.davy.restapi.product.enums.Vat;
import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductDetailResponse;
import com.davy.restapi.shared.TestContainer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductServiceTest extends TestContainer {

    @Autowired
    private ProductService productService;

    @Test
    @Order(1)
    @Transactional
    void shouldSaveProduct() throws IOException {
        ProductRequest request = new ProductRequest(
                "Test Article",
                "Test Description",
                "Test Image",
                250,
                300,
                Vat.STANDARD_RATE,
                (short) 500,
                1L,
                1L
        );
        productService.saveProduct(request);
        var savedProduct = productService.findProductById(12L);
        assertNotNull(savedProduct);
        assertEquals("Test Article", savedProduct.getProduct().getName());
        assertEquals("Test Description", savedProduct.getProduct().getDescription());
    }

    @Test
    @Order(2)
    void shouldGetAllProductsPaginated() {
        var productPaginated = productService.filterAndSearchProductsByNamePageable(
                null,
                null,
                null,
                0,
                null,
                "asc");
        assertThat(productPaginated.get("next")).isEqualTo(true);
        assertThat(productPaginated.get("previous")).isEqualTo(false);
        assertThat(productPaginated.get("count")).isEqualTo(11L);
        assertThat(productPaginated.get("totalPages")).isEqualTo(2);
        assertThat(productPaginated.get("currentPage")).isEqualTo(0);
        assertThat(productPaginated.get("products")).isNotNull();
    }

    @Test
    @Order(3)
    void shouldGetProductById() {
        ProductDetailResponse response_1 = productService.findProductById(1L);
        assertThat(response_1.product.getName()).isEqualTo("Fairy Tale");

        ProductDetailResponse response_2 = productService.findProductById(5L);
        assertThat(response_2.product.getName()).isEqualTo("ChatGPT 02 2023");

        ProductDetailResponse response_3 = productService.findProductById(11L);
        assertThat(response_3.product.getName()).isEqualTo("Suicide Squad: Kill The Justice League - PlayStation 5");
    }


    @Test
    @Order(5)
    void shouldUpdateProduct() {
        ProductRequest request = new ProductRequest(
                "Test Article 2",
                "Test Description 2",
                "Test Image 2",
                250,
                300,
                Vat.STANDARD_RATE,
                (short) 500,
                1L,
                1L
        );

        productService.updateProductById(1L, request);
        var updatedProduct = productService.findProductById(1L);
        assertNotNull(updatedProduct);
        assertEquals("Test Article 2", updatedProduct.getProduct().getName());
        assertEquals("Test Description 2", updatedProduct.getProduct().getDescription());
    }
}