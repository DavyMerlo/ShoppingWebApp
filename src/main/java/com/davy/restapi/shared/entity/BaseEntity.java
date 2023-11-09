package com.davy.restapi.shared.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

//Base class for all the entities
//This entity contains the id, createdAt, updatedAt, deletedAt that will be used
//for all entities.
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "default_gen")
    @Column(nullable = false,updatable = true)
    private Long id;


    @Column(nullable = true,
            updatable = false,
            name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;


    @Column(insertable = false,
            name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;


    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


    @CreatedBy
    @Column(
            nullable = true,
            updatable = false,
            name = "created_by"
    )
    private Long createdBy;

    @LastModifiedBy
    @Column(
            insertable = false,
            name = "updated_by"
    )
    private Long updatedBy;


    public BaseEntity(LocalDateTime createdAt,
                      LocalDateTime updatedAt,
                      LocalDateTime deletedAt,
                      Long createdBy,
                      Long updatedBy) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}