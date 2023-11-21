package com.davy.restapi.product.request;

import com.davy.restapi.product.enums.Vat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
public class ProductRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("purchasePrice")
    private float purchasePrice;

    @JsonProperty("sellingPrice")
    private float sellingPrice;

    @JsonProperty("vat")
    @Enumerated(EnumType.ORDINAL)
    private Vat VAT;

    @JsonProperty("quantity")
    private short quantity;

    @JsonProperty("categoryId")
    private Long categoryId;

    @JsonProperty("subCategoryId")
    private Long subCategoryId;

    @JsonProperty("image")
    private MultipartFile file;

    public ProductRequest(String name,
                          String description,
                          float purchasePrice,
                          float sellingPrice,
                          Vat VAT,
                          short quantity,
                          Long categoryId,
                          Long subCategoryId,
                          MultipartFile file) {
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.VAT = VAT;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.file = file;
    }
}
