package com.davy.restapi.product;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.product.dto.ProductDetailsDTO;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.product.enums.Vat;
import com.davy.restapi.product.dto.ProductRequestDTO;
import com.davy.restapi.product.service.ProductService;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.service.CrudService;
import com.davy.restapi.subcategory.dto.SubCategoryDTO;
import jakarta.transaction.Transactional;
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
        ProductRequestDTO request = new ProductRequestDTO(
                "Test Article Saved",
                "Test Description Saved",
                "https://media.s-bol.com/J2zPpkpP1O6v/EnJOjm/934x1200.jpg",
                12.95F,
                20.99F,
                Vat.STANDARD_RATE,
                (short) 500,
                2L,
                6L
        );
        productService.save(request);
        ProductDetailsDTO savedProduct = (ProductDetailsDTO) productService.findById(12L);
        assertThat(savedProduct)
                .usingRecursiveComparison()
                .isEqualTo(getExpectedSavedProductDetail());
    }

    @Test
    @Order(2)
    void shouldGetProductsOnPageOneWithPageSizeEight() {
        short pageOne = 0;
        short pageSize = 8;
        var productPaginated = productService.filterProductsPageable(
                null,
                null,
                null,
                pageOne,
                pageSize,
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
    @Order(2)
    void shouldGetProductsOnPageThreeWithPageSizeFour() {
        short pageThree = 2;
        short pageSize = 4;
        var productPaginated = productService.filterProductsPageable(
                null,
                null,
                null,
                pageThree,
                pageSize,
                null,
                "asc");
        assertThat(productPaginated.get("next")).isEqualTo(false);
        assertThat(productPaginated.get("previous")).isEqualTo(true);
        assertThat(productPaginated.get("count")).isEqualTo(11L);
        assertThat(productPaginated.get("totalPages")).isEqualTo(3);
        assertThat(productPaginated.get("currentPage")).isEqualTo(2);
        assertThat(productPaginated.get("products")).isNotNull();
    }

    @Test
    @Order(3)
    void shouldGetProductById() {

        ProductDetailsDTO product = (ProductDetailsDTO) productService.findById(5L);
        assertThat(product)
                .usingRecursiveComparison()
                .isEqualTo(getExpectedProductDetail());
    }


    @Test
    @Order(4)
    void shouldUpdateProduct() {
        ProductRequestDTO request = new ProductRequestDTO(
                "Test Article Updated",
                "Test Description Updated",
                "https://media.s-bol.com/J2zPpkpP1O6v/EnJOjm/934x1200.jpg",
                12.95F,
                20.99F,
                Vat.STANDARD_RATE,
                (short) 500,
                2L,
                6L
        );

        productService.updateById(5L, request);
        ProductDetailsDTO updatedProduct = (ProductDetailsDTO) productService.findById(5L);
        assertThat(updatedProduct)
                .usingRecursiveComparison()
                .isEqualTo(getExpectedUpdatedProductDetail());
    }

    private ProductDetailsDTO getExpectedProductDetail(){
        return new ProductDetailsDTO(
                5L,
                "ChatGPT 02 2023",
                "About this magazine:\nGet started with ChatGPT yourself. " +
                        "Learn how to write texts on any subject in no time and how to make " +
                        "your own texts better and more beautiful. In 132 pages we give you explanations, " +
                        "tips and workshops about ChatGPT and you discover other possibilities of AI.",
                "https://media.s-bol.com/J2zPpkpP1O6v/EnJOjm/934x1200.jpg",
                11.95F,
                16.99F,
                Vat.STANDARD_RATE.getVatValue(),
                new CategoryDTO(
                        1L,
                        "Books"
                ),
                new SubCategoryDTO(
                        3L,
                        "Magazine"
                ),
                null
        );
    }

    private ProductDetailsDTO getExpectedSavedProductDetail(){
        return new ProductDetailsDTO(
                12L,
                "Test Article Saved",
                "Test Description Saved",
                "https://media.s-bol.com/J2zPpkpP1O6v/EnJOjm/934x1200.jpg",
                12.95F,
                20.99F,
                Vat.STANDARD_RATE.getVatValue(),
                new CategoryDTO(
                        2L,
                        "Music, Movies & Games"
                ),
                new SubCategoryDTO(
                        6L,
                        "Music"
                ),
                null
        );
    }

    private ProductDetailsDTO getExpectedUpdatedProductDetail(){
        return new ProductDetailsDTO(
                5L,
                "Test Article Updated",
                "Test Description Updated",
                "https://media.s-bol.com/J2zPpkpP1O6v/EnJOjm/934x1200.jpg",
                12.95F,
                20.99F,
                Vat.STANDARD_RATE.getVatValue(),
                new CategoryDTO(
                        2L,
                        "Music, Movies & Games"
                ),
                new SubCategoryDTO(
                        6L,
                        "Music"
                ),
                null
        );
    }
}