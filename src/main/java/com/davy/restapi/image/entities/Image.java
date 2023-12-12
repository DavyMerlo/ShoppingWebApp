package com.davy.restapi.image.entities;

import com.davy.restapi.shared.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "image")
@SequenceGenerator(
        name="default_gen",
        sequenceName = "image_id_seq",
        allocationSize=1)
public class Image extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "imagedata", unique = false, nullable = false, length = 100000)
    private byte[] imageData;

    @Builder
    public Image(LocalDateTime createdAt,
                 LocalDateTime updatedAt,
                 LocalDateTime deletedAt,
                 Long createdBy,
                 Long updatedBy,
                 String name,
                 String type,
                 byte[] imageData,
                 Long id) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }
}
