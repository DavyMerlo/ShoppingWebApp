package com.davy.restapi.address.entity;

import com.davy.restapi.user.entity.UserEntity;
import com.davy.restapi.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user_address")
@SequenceGenerator(
        name="default_gen",
        sequenceName = "address_id_seq",
        allocationSize=1)
public class AddressEntity extends BaseEntity {

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "local_authoriy")
    private String localAuthority;

    @OneToOne(mappedBy = "address")
    private UserEntity user;

    @Builder
    public AddressEntity(LocalDateTime createdAt,
                         LocalDateTime updatedAt,
                         LocalDateTime deletedAt,
                         Long createdBy,
                         Long updatedBy,
                         String street,
                         String houseNumber,
                         String busNumber,
                         String postalCode,
                         String localAuthority,
                         UserEntity user,
                         Long id) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.street = street;
        this.houseNumber = houseNumber;
        this.busNumber = busNumber;
        this.postalCode = postalCode;
        this.localAuthority = localAuthority;
        this.user = user;
    }
}
