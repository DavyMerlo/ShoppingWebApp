package com.davy.restapi.product.entity;

import com.davy.restapi.category.entity.CategoryEntity;
import com.davy.restapi.discount.entity.DiscountEntity;
import com.davy.restapi.inventory.entity.InventoryEntity;
import com.davy.restapi.subcategory.entity.SubCategoryEntity;
import com.davy.restapi.shared.entity.BaseEntity;
import com.davy.restapi.product.enums.Vat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "product")
@SequenceGenerator(
        name="default_gen",
        sequenceName = "product_id_seq",
        allocationSize=1)
public class ProductEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "descr", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "purchase_price")
    private float purchasePrice;

    @Column(name = "selling_price")
    private float sellingPrice;

    @Column(name = "vat")
    @Enumerated(EnumType.ORDINAL)
    private Vat VAT;

    @ManyToOne
    @JoinColumn(name = "subCategory_id")
    private SubCategoryEntity subCategory;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private DiscountEntity discount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private InventoryEntity inventory;


    @Builder
    public ProductEntity(LocalDateTime createdAt,
                         LocalDateTime updatedAt,
                         LocalDateTime deletedAt,
                         Long createdBy,
                         Long updatedBy,
                         String name,
                         String description,
                         String imageUrl,
                         float purchasePrice,
                         float sellingPrice,
                         Vat VAT,
                         SubCategoryEntity subCategory,
                         CategoryEntity category,
                         DiscountEntity discount,
                         InventoryEntity inventory,
                         Long id ) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.VAT = VAT;
        this.subCategory = subCategory;
        this.category = category;
        this.discount = discount;
        this.inventory = inventory;
    }
}
