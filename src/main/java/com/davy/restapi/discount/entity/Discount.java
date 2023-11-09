package com.davy.restapi.discount.entity;

import com.davy.restapi.discount.enums.DiscountStatus;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "discount")
@SequenceGenerator(
        name="default_gen",
        sequenceName = "discount_id_seq",
        allocationSize=1)
public class Discount extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "descr")
    private String description;

    @Column(name = "discount_percent")
    private byte discountPercent;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private DiscountStatus status;

    @OneToOne(mappedBy = "discount")
    private Product product;

    @Builder
    public Discount(LocalDateTime createdAt,
                    LocalDateTime updatedAt,
                    LocalDateTime deletedAt,
                    Long createdBy,
                    Long updatedBy,
                    String name,
                    String description,
                    byte discountPercent,
                    DiscountStatus status,
                    Product product) {
        super(createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.name = name;
        this.description = description;
        this.discountPercent = discountPercent;
        this.status = status;
        this.product = product;
    }
}
