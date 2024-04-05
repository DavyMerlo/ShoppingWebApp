package com.davy.restapi.product.dto;

import com.davy.restapi.product.enums.Vat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class ProductRequestDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("purchasePrice")
    private BigDecimal purchasePrice;

    @JsonProperty("sellingPrice")
    private BigDecimal sellingPrice;

    @JsonProperty("vat")
    @Enumerated(EnumType.ORDINAL)
    private Vat VAT;

    @JsonProperty("quantity")
    private short quantity;

    @JsonProperty("categoryId")
    private Long categoryId;

    @JsonProperty("subCategoryId")
    private Long subCategoryId;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String name,
                             String description,
                             String imageUrl,
                             BigDecimal purchasePrice,
                             BigDecimal sellingPrice,
                             Vat VAT,
                             short quantity,
                             Long categoryId,
                             Long subCategoryId) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.VAT = VAT;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
    }
}
