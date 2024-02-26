package com.davy.restapi.product;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.inventory.entity.Inventory;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.product.enums.Vat;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.subcategory.entity.SubCategory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductRepositoryTest extends TestContainer {

    @Autowired
    private CrudRepository<Product> productRepository;

    @Autowired
    private CrudRepository<Category> categoryRepository;

    @Autowired
    private CrudRepository<SubCategory> subCategoryRepository;

    @DisplayName("Get all products")
    @Test
    @Order(1)
    void shouldGetAllProducts(){
        var products = productRepository.getAll();
        assertThat(products).hasSize(11);
    }

    @DisplayName("Get product with ID = 5")
    @Test
    @Order(2)
    void shouldGetProductById(){
        var product = productRepository.getById(5L);
        assertThat(product.get().getId()).isEqualTo(5L);
        assertThat(product.get().getName()).isEqualTo("ChatGPT 02 2023");
        assertThat(product.get().getCategory().getId()).isEqualTo(1L);
        assertThat(product.get().getCategory().getName()).isEqualTo("Books");
        assertThat(product.get().getCategory().getId()).isEqualTo(1L);
        assertThat(product.get().getSubCategory().getName()).isEqualTo("Magazine");
    }

    @DisplayName("save new product")
    @Test
    @Order(3)
    void shouldSaveProduct(){
        var subCategory = subCategoryRepository.getById(2L);
        var category = categoryRepository.getById(1L);

        var inventory = new Inventory();
        inventory.setQuantity((short) 5000);

        var product = new Product();
        product.setName("Test Article");
        product.setDescription("Test Description");
        product.setPurchasePrice(25);
        product.setSellingPrice(55);
        product.setVAT(Vat.STANDARD_RATE);
        product.setSubCategory(subCategory.get());
        product.setCategory(category.get());
        product.setInventory(inventory);
        productRepository.save(product);
        var savedProduct = productRepository.getById(12L);
        assertNotNull(savedProduct);
        assertEquals("Test Article", savedProduct.get().getName());
        assertEquals("Test Description", savedProduct.get().getDescription());
        assertEquals(25, savedProduct.get().getPurchasePrice());
        assertEquals(55, savedProduct.get().getSellingPrice());
        assertEquals(Vat.STANDARD_RATE, savedProduct.get().getVAT());
        assertEquals(1L, savedProduct.get().getCategory().getId());
        assertEquals("Books", savedProduct.get().getCategory().getName());
        assertEquals(2L, savedProduct.get().getSubCategory().getId());
        assertEquals("Ebooks & Audiobooks", savedProduct.get().getSubCategory().getName());
    }

    @DisplayName("Update product with ID = 5")
    @Test
    @Order(4)
    void shouldUpdateProduct(){
        var subCategory = subCategoryRepository.getById(2L);
        var category = categoryRepository.getById(1L);

        var product = productRepository.getById(5L).get();
        product.setName("Test Article Updated");
        product.setDescription("Test Description Updated");
        product.setPurchasePrice(25);
        product.setSellingPrice(55);
        product.setVAT(Vat.STANDARD_RATE);
        product.setSubCategory(subCategory.get());
        product.setCategory(category.get());
        product.setInventory(new Inventory());
        productRepository.update(product);
        var updatedProduct = productRepository.getById(5L);
        assertNotNull(updatedProduct);
        assertEquals("Test Article Updated", updatedProduct.get().getName());
        assertEquals("Test Description Updated", updatedProduct.get().getDescription());
        assertEquals(25, updatedProduct.get().getPurchasePrice());
        assertEquals(55, updatedProduct.get().getSellingPrice());
        assertEquals(Vat.STANDARD_RATE, updatedProduct.get().getVAT());
        assertEquals(1L, updatedProduct.get().getCategory().getId());
        assertEquals("Books", updatedProduct.get().getCategory().getName());
        assertEquals(2L, updatedProduct.get().getSubCategory().getId());
        assertEquals("Ebooks & Audiobooks", updatedProduct.get().getSubCategory().getName());
    }
}