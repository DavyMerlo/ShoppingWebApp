package com.davy.restapi.product.dto;

import com.davy.restapi.category.dto.CategoryDetailsDTO;
import com.davy.restapi.inventory.dto.Inventory;
import com.davy.restapi.subcategory.dto.SubCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductDetails {

    @JsonProperty("id")
    private  Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("description")
    private String description;

    @JsonProperty("purchasePrice")
    private float purchasePrice;

    @JsonProperty("sellingPrice")
    private float sellingPrice;

    @JsonProperty("vat")
    private int vat;

    @JsonProperty("category")
    private CategoryDetailsDTO category;

    @JsonIgnore
    @JsonProperty("subCategory")
    private SubCategory subCategory;

    @JsonProperty("inventory")
    private Inventory inventory;

    public ProductDetails(Long id,
                          String name,
                          String description,
                          String imageUrl,
                          float purchasePrice,
                          float sellingPrice,
                          int vat,
                          CategoryDetailsDTO category,
                          SubCategory subCategory,
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
