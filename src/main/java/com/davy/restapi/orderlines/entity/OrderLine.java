package com.davy.restapi.orderlines.entity;

import com.davy.restapi.product.entity.Product;
import com.davy.restapi.order.entity.Order;
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
public class OrderLine extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private short quantity;

    @Builder
    public OrderLine(LocalDateTime createdAt,
                     LocalDateTime updatedAt,
                     LocalDateTime deletedAt,
                     Long createdBy,
                     Long updatedBy,
                     Order order,
                     Product product,
                     short quantity,
                     Long id) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}
