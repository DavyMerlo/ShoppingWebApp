package com.davy.restapi.product.entity;

import com.davy.restapi.category.entity.Category;
import com.davy.restapi.discount.entity.Discount;
import com.davy.restapi.inventory.entity.Inventory;
import com.davy.restapi.subcategory.entity.SubCategory;
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
public class Product extends BaseEntity {

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
    private SubCategory subCategory;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private Discount discount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private Inventory inventory;


    @Builder
    public Product(LocalDateTime createdAt,
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
                   SubCategory subCategory,
                   Category category,
                   Discount discount,
                   Inventory inventory,
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
