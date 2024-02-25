package com.davy.restapi.inventory.entity;

import com.davy.restapi.product.entity.Product;
import com.davy.restapi.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "product_inventory")
@SequenceGenerator(
        name="default_gen",
        sequenceName = "product_inventory_id_seq",
        allocationSize=1)
public class Inventory extends BaseEntity implements Serializable {

    @Column(name = "quantity")
    private short quantity;

    @OneToOne(mappedBy = "inventory")
    private Product product;

    @Builder
    public Inventory(LocalDateTime createdAt,
                     LocalDateTime updatedAt,
                     LocalDateTime deletedAt,
                     Long createdBy,
                     Long updatedBy,
                     short quantity,
                     Product product,
                     Long id) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.quantity = quantity;
        this.product = product;
    }
}
