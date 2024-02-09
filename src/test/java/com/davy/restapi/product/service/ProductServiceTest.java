package com.davy.restapi.product.service;

import com.davy.restapi.product.enums.Vat;
import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductDetailResponse;
import com.davy.restapi.shared.TestContainer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

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

//
//    @Test
//    @Order(5)
//    void shouldBeAbleToUpdateProduct() {
//        ProductRequest request = new ProductRequest(
//                "Davy",
//                "Test",
//                250,
//                300,
//                Vat.STANDARD_RATE,
//                (short) 500,
//                Long.parseLong("1"),
//                Long.parseLong("1"),
//                null
//        );
//
//        ProductResponse response = productService.updateProductById(Long.parseLong("1"), request);
//        assertThat(response.getProduct().getName()).isEqualTo("Davy");
//        assertThat(response.getProduct().getDescription()).isEqualTo("Test");
//    }
}