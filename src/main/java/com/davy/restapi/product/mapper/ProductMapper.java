package com.davy.restapi.product.mapper;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.category.dto.CategoryRequestDTO;
import com.davy.restapi.category.entity.CategoryEntity;
import com.davy.restapi.inventory.entity.InventoryEntity;
import com.davy.restapi.product.dto.ProductDTO;
import com.davy.restapi.product.dto.ProductDetailsDTO;
import com.davy.restapi.product.dto.ProductRequestDTO;
import com.davy.restapi.product.entity.ProductEntity;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.subcategory.dto.SubCategoryDTO;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import com.davy.restapi.subcategory.entity.SubCategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductMapper implements ObjectMapper<ProductRequestDTO, ProductEntity> {

    private final CrudRepository<CategoryEntity> categoryRepository;
    private final CrudRepository<SubCategoryEntity> subCategoryRepository;
    private final ObjectMapper<CategoryRequestDTO, CategoryEntity> categoryMapper;
    private final ObjectMapper<SubCategoryRequestDTO, SubCategoryEntity> subCategoryMapper;

    @Override
    public ProductEntity mapSourceToDestination(ProductRequestDTO source, ProductEntity destination) {

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
        destination.setInventory(new InventoryEntity());
        destination.setCategory(category);
        destination.setSubCategory(subcategory);
        return destination;
    }

    @Override
    public ProductDTO mapToDto(ProductEntity entity) {
        return new ProductDTO(
                entity.getId(),
                entity.getName(),
                entity.getSellingPrice()
        );
    }

    @Override
    public ProductDetailsDTO mapToDetailsDto(ProductEntity entity) {

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
    public ProductEntity mapToEntity(ProductRequestDTO request) {

        var category = categoryRepository
                .getById(request.getCategoryId())
                .stream().findFirst()
                .get();

        var subcategory = subCategoryRepository
                .getById(request.getSubCategoryId())
                .stream().findFirst()
                .get();


        return ProductEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .purchasePrice(request.getPurchasePrice())
                .sellingPrice(request.getSellingPrice())
                .VAT(request.getVAT())
                .category(category)
                .subCategory(subcategory)
                .inventory(new InventoryEntity())
                .build();
    }

    @Override
    public Object mapToListDto(ProductEntity entity) {
        return null;
    }
}
