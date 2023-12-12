package com.davy.restapi.order.entity;

import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.orderlines.entity.OrderLines;
import com.davy.restapi.payment.entity.Payment;
import com.davy.restapi.user.entity.User;
import com.davy.restapi.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "order_details")
@SequenceGenerator(
        name="default_gen",
        sequenceName = "order_details_id_seq",
        allocationSize=1)
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderLines> orderItems;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;


    @Builder
    public Order(LocalDateTime createdAt,
                 LocalDateTime updatedAt,
                 LocalDateTime deletedAt,
                 Long createdBy,
                 Long updatedBy,
                 User user,
                 OrderStatus status,
                 List<OrderLines> orderItems,
                 Payment payment,
                 Long id) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.user = user;
        this.status = status;
        this.orderItems = orderItems;
        this.payment = payment;
    }
}
