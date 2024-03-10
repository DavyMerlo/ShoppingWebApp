package com.davy.restapi.orderlines.entity;

import com.davy.restapi.product.entity.ProductEntity;
import com.davy.restapi.order.entity.OrderEntity;
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
@Table(name = "order_lines")
@SequenceGenerator(
        name="default_gen",
        sequenceName = "order_lines_id_seq",
        allocationSize=1)
public class OrderLineEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private short quantity;

    @Builder
    public OrderLineEntity(LocalDateTime createdAt,
                           LocalDateTime updatedAt,
                           LocalDateTime deletedAt,
                           Long createdBy,
                           Long updatedBy,
                           ProductEntity product,
                           short quantity,
                           Long id) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.product = product;
        this.quantity = quantity;
    }
}
