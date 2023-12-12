package com.davy.restapi.subcategory.entity;

import com.davy.restapi.category.entity.Category;
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
public class SubCategory extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public SubCategory(LocalDateTime createdAt,
                       LocalDateTime updatedAt,
                       LocalDateTime deletedAt,
                       Long createdBy,
                       Long updatedBy,
                       String name,
                       Category category,
                       Long id) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.name = name;
        this.category = category;
    }
}
