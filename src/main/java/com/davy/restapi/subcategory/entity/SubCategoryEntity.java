package com.davy.restapi.subcategory.entity;

import com.davy.restapi.category.entity.CategoryEntity;
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
@Table(name = "product_sub_category")
@SequenceGenerator(
        name="default_gen",
        sequenceName = "sub_category_id_seq",
        allocationSize=1)
public class SubCategoryEntity extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Builder
    public SubCategoryEntity(LocalDateTime createdAt,
                             LocalDateTime updatedAt,
                             LocalDateTime deletedAt,
                             Long createdBy,
                             Long updatedBy,
                             String name,
                             CategoryEntity category,
                             Long id) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.name = name;
        this.category = category;
    }
}
