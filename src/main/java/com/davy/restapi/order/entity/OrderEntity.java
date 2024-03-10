package com.davy.restapi.order.entity;

import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.orderlines.entity.OrderLineEntity;
import com.davy.restapi.payment.entity.PaymentEntity;
import com.davy.restapi.user.entity.UserEntity;
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
public class OrderEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity user;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderLineEntity> orderItems;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private PaymentEntity payment;


    @Builder
    public OrderEntity(LocalDateTime createdAt,
                       LocalDateTime updatedAt,
                       LocalDateTime deletedAt,
                       Long createdBy,
                       Long updatedBy,
                       UserEntity user,
                       OrderStatus status,
                       List<OrderLineEntity> orderItems,
                       PaymentEntity payment,
                       Long id) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.user = user;
        this.status = status;
        this.orderItems = orderItems;
        this.payment = payment;
    }
}
