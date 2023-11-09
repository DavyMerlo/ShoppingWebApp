package com.davy.restapi.product.service;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.category.repository.CategoryRepository;
import com.davy.restapi.inventory.dto.InventoryItems;
import com.davy.restapi.inventory.entity.Inventory;
import com.davy.restapi.inventory.mapper.InventoryItemsMapper;
import com.davy.restapi.inventory.repository.InventoryRepository;
import com.davy.restapi.inventory.request.InventoryCreateRequest;
import com.davy.restapi.inventory.request.InventoryUpdateRequest;
import com.davy.restapi.inventory.service.InventoryService;
import com.davy.restapi.product.dto.ProductDetails;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.product.mapper.ProductMapper;
import com.davy.restapi.product.repository.ProductRepository;
import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductListResponse;
import com.davy.restapi.product.response.ProductResponse;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.subcategory.dto.SubCategoryItems;
import com.davy.restapi.subcategory.mapper.SubCategoryItemsMapper;
import com.davy.restapi.subcategory.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductMapper productMapper;
    private final SubCategoryItemsMapper subCategoryItemsMapper;
    private final InventoryItemsMapper inventoryItemsMapper;

    @Override
    public ProductListResponse findAllProducts(int page, int size) {

        var pageableSorted = PageRequest.of(page, size, Sort.by("id"));
        var response = new ProductListResponse();
        Page<Product> productPage = productRepository.findAll(pageableSorted);
        List<Product> products = productPage.getContent();

        if(products.isEmpty()){
            ThrowException.objectException("Products");
        }
        response.products = products
                .stream()
                .map(productMapper)
                .collect(Collectors.toList());

        for(var item : response.products){
            var subCategoryId = item.getSubCategory().getId();
            var inventoryId = item.getInventory().id();
            var subCategory = mapAndGetSubCategoryItems(subCategoryId);
            var inventory = mapAndGetInventoryItems(inventoryId);
            item.getCategory().setSubCategory(subCategory);
            item.setInventory(inventory);
        }
        return response;
    }

    @Override
    public ProductResponse findProductById(Long id) {

        var response = new ProductResponse();
        if(productRepository.getProductById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Product");
        }
        var product = mapAndGetProductDetails(id);
        var subCategory = mapAndGetSubCategoryItems(product.getSubCategory().getId());
        response.product = product;
        response.product.getCategory().setSubCategory(subCategory);
        return response;
    }

    @Override
    public ProductListResponse saveProduct(ProductRequest request) {

        checkIfCategoryIdAndSubCategoryIdExists(request);
        var inventory = Inventory.builder()
                .quantity(request.getQuantity())
                .build();
        var savedInventory = inventoryRepository.saveInventory(inventory);
        var product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .purchasePrice(request.getPurchasePrice())
                .sellingPrice(request.getSellingPrice())
                .VAT(request.getVAT())
                .inventory(savedInventory)
                .category(categoryRepository.getCategoryById(request.getCategoryId()).get())
                .subCategory(subCategoryRepository.getSubCategoryById(request.getSubCategoryId()).get())
                .build();
        productRepository.saveProduct(product);
        List<Product> products = productRepository.findAll();
        return findAllProducts(0, products.size());
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest request) {

        checkIfCategoryIdAndSubCategoryIdExists(request);

        var product = productRepository.getProductById(id).get();
        var inventory = inventoryRepository.getInventoryById(product.getInventory().getId());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPurchasePrice(request.getPurchasePrice());
        product.setSellingPrice(request.getSellingPrice());
        product.setVAT(request.getVAT());
        product.setInventory(inventory.get());
        product.setCategory(categoryRepository.getCategoryById(request.getCategoryId()).get());
        product.setSubCategory(subCategoryRepository.getSubCategoryById(request.getSubCategoryId()).get());
        product.setUpdatedAt(LocalDateTime.now());
        inventoryRepository.updateInventory(inventory.get());
        productRepository.updateProduct(product);
        return this.findProductById(product.getId());


    }

    private void checkIfCategoryIdAndSubCategoryIdExists(ProductRequest request){
        if (!categoryRepository.existsById(request.getCategoryId())) {
            ThrowException.objectByIdException(request.getCategoryId(), "Category");
        } else if (!inventoryRepository.existsById(request.getSubCategoryId())) {
            ThrowException.objectByIdException(request.getSubCategoryId(), "Subcategory");
        }
    }

    private SubCategoryItems mapAndGetSubCategoryItems(Long subCategoryIdd){
        return subCategoryRepository.getSubCategoryById(subCategoryIdd)
                .stream()
                .map(subCategoryItemsMapper)
                .findFirst()
                .get();
    }

    private InventoryItems mapAndGetInventoryItems(Long inventoryId){
        return inventoryRepository.getInventoryById(inventoryId)
                .stream()
                .map(inventoryItemsMapper)
                .findFirst()
                .get();
    }

    private ProductDetails mapAndGetProductDetails(Long productId){
        return  productRepository.getProductById(productId)
                .stream()
                .map(productMapper)
                .findFirst()
                .get();
    }
}
