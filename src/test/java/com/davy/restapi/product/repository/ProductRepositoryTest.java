package com.davy.restapi.product.repository;

import com.davy.restapi.category.repository.CategoryRepository;
import com.davy.restapi.inventory.entity.Inventory;
import com.davy.restapi.inventory.repository.InventoryRepository;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.product.enums.Vat;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.subcategory.repository.SubCategoryRepository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    @Order(2)
    void shouldBeContain10Products(){
        var products = productRepository.getAllProducts();
        assertThat(products).hasSize(10);
    }

    @Test
    @Order(3)
    void shouldBeAbleToGetProductById(){
        var product = productRepository.getProductById(Long.parseLong("1"));
        assertThat(product.get().getName()).isEqualTo("Fairy Tale");
    }

    @Test
    @Order(4)
    void shouldBeAbleToSaveProduct(){
        Long subCatId = Long.parseLong("1");
        Long CatId = Long.parseLong("1");

        var subCategory = subCategoryRepository.getSubCategoryById(subCatId);
        var category = categoryRepository.getCategoryById(CatId);

        var inventory = new Inventory();
        inventory.setQuantity((short) 5000);

        var product = new Product();
        product.setName("Davy");
        product.setDescription("Merlo");
        product.setPurchasePrice(250);
        product.setSellingPrice(550);
        product.setVAT(Vat.STANDARD_RATE);
        product.setSubCategory(subCategory.get());
        product.setCategory(category.get());
        product.setInventory(inventory);

        productRepository.saveProduct(product);
        List<Product> products = new ArrayList<>();
        productRepository.getAllProducts().forEach(products::add);

        assertThat(products).hasSize(11);
        assertThat(products.get(10).getId()).isEqualTo(11);
        assertThat(products.get(10).getName()).isEqualTo("Davy");

    }

    @Test
    @Order(5)
    void shouldBeAbleToUpdateProduct(){
        Long productId = Long.parseLong("1");
        Long subCatId = Long.parseLong("1");
        Long CatId = Long.parseLong("1");
        Long InventoryId = Long.parseLong("1");

        var subCategory = subCategoryRepository.getSubCategoryById(subCatId);
        var category = categoryRepository.getCategoryById(CatId);

        var inventory = inventoryRepository.getInventoryById(InventoryId);
        inventory.get().setQuantity((short) 500);

        var product = productRepository.getProductById(productId).get();
        product.setName("Test");
        product.setDescription("Test");
        product.setPurchasePrice(250);
        product.setSellingPrice(550);
        product.setVAT(Vat.STANDARD_RATE);
        product.setSubCategory(subCategory.get());
        product.setCategory(category.get());
        product.setInventory(inventory.get());

        productRepository.updateProduct(product);
        var createdProduct = productRepository.getProductById(productId).get();
        assertThat(createdProduct.getName()).isEqualTo("Test");
    }


}