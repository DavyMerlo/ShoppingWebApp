package com.davy.restapi.product.mapper;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.category.dto.CategoryRequestDTO;
import com.davy.restapi.category.entity.Category;
import com.davy.restapi.inventory.entity.Inventory;
import com.davy.restapi.product.dto.ProductDTO;
import com.davy.restapi.product.dto.ProductDetailsDTO;
import com.davy.restapi.product.dto.ProductRequestDTO;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.subcategory.dto.SubCategoryDTO;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import com.davy.restapi.subcategory.entity.SubCategory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductMapper implements ObjectMapper<ProductRequestDTO, Product> {

    private final CrudRepository<Category> categoryRepository;
    private final CrudRepository<SubCategory> subCategoryRepository;
    private final ObjectMapper<CategoryRequestDTO, Category> categoryMapper;
    private final ObjectMapper<SubCategoryRequestDTO, SubCategory> subCategoryMapper;

    @Override
    public Product mapSourceToDestination(ProductRequestDTO source, Product destination) {

        var category = categoryRepository
                .getById(source.getCategoryId())
                .stream().findFirst()
                .get();

        var subcategory = subCategoryRepository
                .getById(source.getSubCategoryId())
                .stream().findFirst()
                .get();

        destination.setName(source.getName());
        destination.setDescription(source.getDescription());
        destination.setImageUrl(source.getImageUrl());
        destination.setPurchasePrice(source.getPurchasePrice());
        destination.setSellingPrice(source.getSellingPrice());
        destination.setVAT(source.getVAT());
        destination.setInventory(new Inventory());
        destination.setCategory(category);
        destination.setSubCategory(subcategory);
        return destination;
    }

    @Override
    public ProductDTO mapToDto(Product entity) {
        return new ProductDTO(
                entity.getId(),
                entity.getName()
        );
    }

    @Override
    public ProductDetailsDTO mapToDetailsDto(Product entity) {

        var category = categoryRepository
                .getById(entity.getCategory().getId())
                .stream()
                .map(categoryMapper::mapToDto)
                .findFirst()
                .get();

        var subCategory = subCategoryRepository
                .getById(entity.getSubCategory().getId())
                .stream()
                .map(subCategoryMapper::mapToDto)
                .findFirst()
                .get();

        return new ProductDetailsDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getPurchasePrice(),
                entity.getSellingPrice(),
                entity.getVAT().getVatValue(),
                (CategoryDTO) category,
                (SubCategoryDTO) subCategory,
                null
        );
    }

    @Override
    public Product mapToEntity(ProductRequestDTO request) {

        var category = categoryRepository
                .getById(request.getCategoryId())
                .stream().findFirst()
                .get();

        var subcategory = subCategoryRepository
                .getById(request.getSubCategoryId())
                .stream().findFirst()
                .get();


        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .purchasePrice(request.getPurchasePrice())
                .sellingPrice(request.getSellingPrice())
                .VAT(request.getVAT())
                .category(category)
                .subCategory(subcategory)
                .inventory(new Inventory())
                .build();
    }

    @Override
    public Object mapToListDto(Product entity) {
        return null;
    }
}
