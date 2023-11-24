package com.davy.restapi.product.service;

import com.davy.restapi.category.repository.CategoryRepository;
import com.davy.restapi.inventory.entity.Inventory;
import com.davy.restapi.inventory.repository.InventoryRepository;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductMapper productMapper;
    private final SubCategoryItemsMapper subCategoryItemsMapper;

    public Map<String, Object> findAllProductsPageable(int page) {

        var pageableSorted = PageRequest.of(page, 5, Sort.by("id"));
        Page<Product> productPage =
                productRepository.findAll(pageableSorted);
        return mappedProductPage(productPage);
    }

    public Map<String, Object> findByCategoryIdAndSubCategoryIdPageable(Long catId,
                                                                        Long subCatId,
                                                                        int page) {
        if(catId == null && subCatId == null){
            return findAllProductsPageable(page);
        }
        Pageable pageable = PageRequest.of(page, 5);
        Page<Product> productPage =
                productRepository.findAllAndFindByCategoryIdAAndSubCategoryId(catId, subCatId, pageable);
        return mappedProductPage(productPage);
    }

    @Override
    public ProductResponse findProductById(Long id) {

        var response = new ProductResponse();
        if(productRepository.getProductById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Product");
        }
        var product = mappedProductDetails(id);
        var subCategory = mappedSubCategoryItems(product.getSubCategory().getId());
        response.product = product;
        response.product.getCategory().setSubCategory(subCategory);
        return response;
    }

    @Override
    public Map<String, Object> saveProduct(ProductRequest request) {

        checkIfCategoryIdAndSubCategoryIdExists(request);
        var inventory = Inventory.builder()
                .quantity(request.getQuantity())
                .build();
        var savedInventory = inventoryRepository.saveInventory(inventory);
        var product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .purchasePrice(request.getPurchasePrice())
                .sellingPrice(request.getSellingPrice())
                .VAT(request.getVAT())
                .inventory(savedInventory)
                .category(categoryRepository.getCategoryById(request.getCategoryId()).get())
                .subCategory(subCategoryRepository.getSubCategoryById(request.getSubCategoryId()).get())
                .build();
        productRepository.saveProduct(product);
        return this.findAllProductsPageable(0);
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest request) {

        checkIfCategoryIdAndSubCategoryIdExists(request);

        var product = productRepository.getProductById(id).get();
        var inventory = inventoryRepository.getInventoryById(product.getInventory().getId());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setImageUrl(request.getImageUrl());
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

    private SubCategoryItems mappedSubCategoryItems(Long subCategoryIdd){
        return subCategoryRepository.getSubCategoryById(subCategoryIdd)
                .stream()
                .map(subCategoryItemsMapper)
                .findFirst()
                .get();
    }

    private ProductDetails mappedProductDetails(Long productId){
        return  productRepository.getProductById(productId)
                .stream()
                .map(productMapper)
                .findFirst()
                .get();
    }

    private Map<String, Object> mappedProductPage(Page productPage){
        List<Product> products = productPage.getContent();
        Map<String, Object> mappedProducts = new HashMap<>();
        List<ProductDetails> productDetails = new ArrayList<>();

        if(products.isEmpty()){
            ThrowException.objectException("Products");
        }

        var productsTest = products
                .stream()
                .map(productMapper)
                .toList();

        for(var item : productsTest){
            var subCategoryId = item.getSubCategory().getId();
            var subCategory = mappedSubCategoryItems(subCategoryId);
            item.getCategory().setSubCategory(subCategory);
            productDetails.add(item);
        }

        mappedProducts.put("products", productDetails);
        mappedProducts.put("currentPage", productPage.getNumber());
        mappedProducts.put("count", productPage.getTotalElements());
        mappedProducts.put("totalPages", productPage.getTotalPages());
        mappedProducts.put("pageSize", productPage.getSize());
        mappedProducts.put("next", productPage.nextPageable().isPaged());
        mappedProducts.put("previous", productPage.previousPageable().isPaged());
        return mappedProducts;
    }
}
