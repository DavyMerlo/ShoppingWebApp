package com.davy.restapi.product;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.inventory.entity.Inventory;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.product.enums.Vat;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.subcategory.entity.SubCategory;

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

    @Test
    @Order(1)
    void shouldGetAllProducts(){
        var products = productRepository.getAll();
        assertThat(products).hasSize(11);
    }

    @Test
    @Order(2)
    void shouldGetProductById(){
        var product_1 = productRepository.getById(1L);
        assertThat(product_1.get().getName()).isEqualTo("Fairy Tale");

        var product_2 = productRepository.getById(5L);
        assertThat(product_2.get().getName()).isEqualTo("ChatGPT 02 2023");

        var product_3 = productRepository.getById(11L);
        assertThat(product_3.get().getName()).isEqualTo("Suicide Squad: Kill The Justice League - PlayStation 5");
    }

    @Test
    @Order(3)
    void shouldSaveProduct(){
        var subCategory = subCategoryRepository.getById(1L);
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
    }

    @Test
    @Order(4)
    void shouldUpdateProduct(){
        var subCategory = subCategoryRepository.getById(1L);
        var category = categoryRepository.getById(1L);

//        var inventory = inventoryRepository.getInventoryById(1L);
//        inventory.get().setQuantity((short) 500);

        var product = productRepository.getById(5L).get();
        product.setName("Test Article Updated");
        product.setDescription("Test Description Updated");
        product.setPurchasePrice(250);
        product.setSellingPrice(550);
        product.setVAT(Vat.STANDARD_RATE);
        product.setSubCategory(subCategory.get());
        product.setCategory(category.get());
        product.setInventory(new Inventory());
        productRepository.update(product);
        var savedProduct = productRepository.getById(5L);
        assertNotNull(savedProduct);
        assertEquals("Test Article Updated", savedProduct.get().getName());
        assertEquals("Test Description Updated", savedProduct.get().getDescription());
    }
}