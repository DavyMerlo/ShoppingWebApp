package com.davy.restapi.product;

import com.davy.restapi.product.dto.ProductDetailsDTO;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.product.enums.Vat;
import com.davy.restapi.product.dto.ProductRequestDTO;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.service.CrudService;
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
    private CrudService<Product, ProductRequestDTO> productService;

    @Test
    @Order(1)
    @Transactional
    void shouldSaveProduct() throws IOException {
        ProductRequestDTO request = new ProductRequestDTO(
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
        productService.save(request);
        ProductDetailsDTO savedProduct = (ProductDetailsDTO) productService.findById(12L);
        assertNotNull(savedProduct);
        assertEquals("Test Article", savedProduct.getName());
        assertEquals("Test Description", savedProduct.getDescription());
    }

//    @Test
//    @Order(2)
//    void shouldGetAllProductsPaginated() {
//        var productPaginated = productService.filterAndSearchProductsByNamePageable(
//                null,
//                null,
//                null,
//                0,
//                null,
//                "asc");
//        assertThat(productPaginated.get("next")).isEqualTo(true);
//        assertThat(productPaginated.get("previous")).isEqualTo(false);
//        assertThat(productPaginated.get("count")).isEqualTo(11L);
//        assertThat(productPaginated.get("totalPages")).isEqualTo(2);
//        assertThat(productPaginated.get("currentPage")).isEqualTo(0);
//        assertThat(productPaginated.get("products")).isNotNull();
//    }
//
    @Test
    @Order(2)
    void shouldGetProductById() {

        ProductDetailsDTO product = (ProductDetailsDTO) productService.findById(5L);
        assertThat(product.getName()).isEqualTo("ChatGPT 02 2023");
    }


    @Test
    @Order(3)
    void shouldUpdateProduct() {
        ProductRequestDTO request = new ProductRequestDTO(
                "Test Article Updated",
                "Test Description Updated",
                "Test Image 2",
                250,
                300,
                Vat.STANDARD_RATE,
                (short) 500,
                1L,
                1L
        );

        productService.updateById(5L, request);
        ProductDetailsDTO updatedProduct = (ProductDetailsDTO) productService.findById(5L);
        assertNotNull(updatedProduct);
        assertEquals("Test Article Updated", updatedProduct.getName());
        assertEquals("Test Description Updated", updatedProduct.getDescription());
    }
}