package com.davy.restapi.card.entity;

import com.davy.restapi.user.entity.UserEntity;
import com.davy.restapi.shared.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "customer_card")
@SequenceGenerator(
        name="default_gen",
        sequenceName = "customer_card_id_seq",
        allocationSize=1)
public class CustomerCardEntity extends BaseEntity {

    @Size(min = 2, message = "Car number cannot be shorten then 2")
    @Size(max = 50, message = "Car number cannot be longer then 50")
    @NotNull(message = "Card number cannot be empty")
    private String number;

    @OneToOne(mappedBy = "customerCard")
    private UserEntity user;

    @Column(name = "points")
    private byte points;

    @Builder
    public CustomerCardEntity(LocalDateTime createdAt,
                              LocalDateTime updatedAt,
                              LocalDateTime deletedAt,
                              Long createdBy,
                              Long updatedBy,
                              String number,
                              UserEntity user,
                              byte points,
                              Long id) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.number = number;
        this.user = user;
        this.points = points;
    }
}
