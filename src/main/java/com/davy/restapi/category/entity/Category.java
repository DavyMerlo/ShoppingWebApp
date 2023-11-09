package com.davy.restapi.category.entity;

import com.davy.restapi.subcategory.entity.SubCategory;
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
@Table(name = "product_category")
@SequenceGenerator(
        name="default_gen",
        sequenceName = "category_id_seq",
        allocationSize=1)
public class Category extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<SubCategory> subcategories;

    @Builder
    public Category(LocalDateTime createdAt,
                    LocalDateTime updatedAt,
                    LocalDateTime deletedAt,
                    Long createdBy,
                    Long updatedBy,
                    String name,
                    List<SubCategory> subcategories) {
        super(createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.name = name;
        this.subcategories = subcategories;
    }
}
