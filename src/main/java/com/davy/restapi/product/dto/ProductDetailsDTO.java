package com.davy.restapi.product.dto;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.inventory.dto.Inventory;
import com.davy.restapi.subcategory.dto.SubCategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class ProductDetailsDTO {

    @JsonProperty("id")
    private  Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("description")
    private String description;

    @JsonProperty("purchasePrice")
    private BigDecimal purchasePrice;

    @JsonProperty("sellingPrice")
    private BigDecimal sellingPrice;

    @JsonProperty("vat")
    private int vat;

    @JsonProperty("category")
    private CategoryDTO category;

    @JsonProperty("subCategory")
    private SubCategoryDTO subCategory;

    @JsonProperty("inventory")
    private Inventory inventory;

    public ProductDetailsDTO(Long id,
                             String name,
                             String description,
                             String imageUrl,
                             BigDecimal purchasePrice,
                             BigDecimal sellingPrice,
                             int vat,
                             CategoryDTO category,
                             SubCategoryDTO subCategory,
                             Inventory inventory
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.vat = vat;
        this.category = category;
        this.subCategory = subCategory;
        this.inventory = inventory;
    }
}
