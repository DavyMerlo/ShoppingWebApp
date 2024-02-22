package com.davy.restapi.product;

import com.davy.restapi.category.repository.CategoryRepository;
import com.davy.restapi.inventory.entity.Inventory;
import com.davy.restapi.inventory.repository.InventoryRepository;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.product.enums.Vat;
import com.davy.restapi.product.repository.ProductRepository;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.subcategory.repository.SubCategoryRepository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductRepositoryTest extends TestContainer {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    @Order(1)
    void shouldGetAllProducts(){
        var products = productRepository.getAllProducts();
        assertThat(products).hasSize(11);
    }

    @Test
    @Order(2)
    void shouldGetProductById(){
        var product_1 = productRepository.getProductById(1L);
        assertThat(product_1.get().getName()).isEqualTo("Fairy Tale");

        var product_2 = productRepository.getProductById(5L);
        assertThat(product_2.get().getName()).isEqualTo("ChatGPT 02 2023");

        var product_3 = productRepository.getProductById(11L);
        assertThat(product_3.get().getName()).isEqualTo("Suicide Squad: Kill The Justice League - PlayStation 5");
    }

    @Test
    @Order(3)
    void shouldSaveProduct(){
        var subCategory = subCategoryRepository.findById(1L);
        var category = categoryRepository.findById(1L);

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
        productRepository.saveProduct(product);
        var savedProduct = productRepository.getProductById(12L);
        assertNotNull(savedProduct);
        assertEquals("Test Article", savedProduct.get().getName());
        assertEquals("Test Description", savedProduct.get().getDescription());
    }

    @Test
    @Order(4)
    void shouldUpdateProduct(){
        var subCategory = subCategoryRepository.findById(1L);
        var category = categoryRepository.findById(1L);

        var inventory = inventoryRepository.getInventoryById(1L);
        inventory.get().setQuantity((short) 500);

        var product = productRepository.getProductById(1L).get();
        product.setName("Test Article 2");
        product.setDescription("Test Description 2");
        product.setPurchasePrice(250);
        product.setSellingPrice(550);
        product.setVAT(Vat.STANDARD_RATE);
        product.setSubCategory(subCategory.get());
        product.setCategory(category.get());
        product.setInventory(inventory.get());
        productRepository.updateProduct(product);
        var savedProduct = productRepository.getProductById(1L);
        assertNotNull(savedProduct);
        assertEquals("Test Article 2", savedProduct.get().getName());
        assertEquals("Test Description 2", savedProduct.get().getDescription());
    }
}