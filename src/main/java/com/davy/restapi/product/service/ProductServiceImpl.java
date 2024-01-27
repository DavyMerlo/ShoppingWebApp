package com.davy.restapi.product.service;

import com.davy.restapi.category.repository.CategoryRepository;
import com.davy.restapi.inventory.entity.Inventory;
import com.davy.restapi.inventory.repository.InventoryRepository;
import com.davy.restapi.product.dto.ProductDetails;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.product.mapper.ProductMapper;
import com.davy.restapi.product.repository.ProductRepository;
import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.response.ProductResponse;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.subcategory.dto.SubCategory;
import com.davy.restapi.subcategory.mapper.SubCategoryMapper;
import com.davy.restapi.subcategory.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    private final SubCategoryMapper subCategoryMapper;

    public Map<String, Object> findAllProductsPageable(int page) {

        var pageableSorted = PageRequest.of(page, 8, Sort.by("id"));
        Page<Product> productPage =
                productRepository.findAll(pageableSorted);
        return mappedProductPage(productPage);
    }

    public Map<String, Object> filterAndSearchProductsByNamePageable(Long catId,
                                                                     Long subCatId,
                                                                     String name,
                                                                     int page,
                                                                     String sortBy,
                                                                     String sortOrder) {
        Pageable pageable = makePageable(page, sortBy, sortOrder);
        Specification<Product> spec = specification(catId, subCatId, name);
        Page<Product> productPage = productRepository.findAll(spec, pageable);
        return mappedProductPage(productPage);
    }

    @Override
    public ProductResponse findProductById(Long id) {
        if(productRepository.getProductById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Product");
        }
        var product = mappedProductDetails(id);
        var subCategoryId = product.getSubCategory().getId();
        var subCategory = mappedSubCategoryItems(subCategoryId);
        product.getCategory().setSubCategory(subCategory);
        return ProductResponse.builder()
                .product(product)
                .build();
    }

    @Override
    public ProductResponse saveProduct(ProductRequest request) {
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
        return this.findProductById(product.getId());
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest request) {
        Product productToUpdate = productRepository.getProductById(id).get();
        Product updatedProduct = Product.builder()
                .id(productToUpdate.getId())
                .name(request.getName())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .purchasePrice(request.getPurchasePrice())
                .sellingPrice(request.getSellingPrice())
                .VAT(request.getVAT())
                .category(categoryRepository.getCategoryById(request.getCategoryId()).get())
                .subCategory(subCategoryRepository.getSubCategoryById(request.getSubCategoryId()).get())
                .updatedAt(LocalDateTime.now())
                .inventory(Inventory.builder()
                        .id(productToUpdate.getInventory().getId())
                        .quantity(request.getQuantity())
                        .build())
                .build();
        productRepository.updateProduct(updatedProduct);
        return findProductById(updatedProduct.getId());
    }

    private void checkIfCategoryIdAndSubCategoryIdExists(ProductRequest request){
        if (!categoryRepository.existsById(request.getCategoryId())) {
            ThrowException.objectByIdException(request.getCategoryId(), "Category");
        } else if (!inventoryRepository.existsById(request.getSubCategoryId())) {
            ThrowException.objectByIdException(request.getSubCategoryId(), "Subcategory");
        }
    }

    private SubCategory mappedSubCategoryItems(Long subCategoryIdd){
        return subCategoryRepository.getSubCategoryById(subCategoryIdd)
                .stream()
                .map(subCategoryMapper)
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


    private Pageable makePageable(int page, String sortBy, String sortOrder) {
        if (sortBy != null && sortOrder != null) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
            return PageRequest.of(page, 8, sort);
        } else {
            return PageRequest.of(page, 8);
        }
    }

    private Specification<Product> specification(Long catId, Long subCatId, String name) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpecification.nameLike(name));
        }
        if (catId != null) {
            spec = spec.and(ProductSpecification.byCategory(catId));
        }
        if (subCatId != null) {
            spec = spec.and(ProductSpecification.bySubCategory(subCatId));
        }
        if (catId != null && subCatId != null) {
            spec = spec.and(ProductSpecification.byCategoryAndSubCategory(catId, subCatId));
        }
        return spec;
    }
}
