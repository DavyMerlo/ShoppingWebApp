package com.davy.restapi.product.mapper;

import com.davy.restapi.category.mapper.CategoryDetailsMapper;
import com.davy.restapi.inventory.mapper.InventoryItemsMapper;
import com.davy.restapi.product.dto.ProductDetails;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.subcategory.mapper.SubCategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class ProductMapper implements Function<Product, ProductDetails> {

    private final SubCategoryMapper subCategoryMapper;
    private final CategoryDetailsMapper categoryDetailsMapper;
    private final InventoryItemsMapper inventoryItemsMapper;

    @Override
    public ProductDetails apply(Product product) {
        return new ProductDetails(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImageUrl(),
                product.getPurchasePrice(),
                product.getSellingPrice(),
                product.getVAT().ordinal(),
                categoryDetailsMapper.apply(product.getCategory()),
                subCategoryMapper.apply(product.getSubCategory()),
                inventoryItemsMapper.apply(product.getInventory())
        );
    }
}
