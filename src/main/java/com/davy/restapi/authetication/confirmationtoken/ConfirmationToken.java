package com.davy.restapi.authetication.confirmationtoken;

import com.davy.restapi.shared.entity.BaseEntity;
import com.davy.restapi.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name="default_gen",
        sequenceName = "confirmation_token_id_seq",
        allocationSize=1)
public class ConfirmationToken extends BaseEntity {

    @Column(unique = false)
    private String token;

    @Column(unique = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id")
    private UserEntity user;

    public ConfirmationToken(Long id,
                             LocalDateTime createdAt,
                             LocalDateTime updatedAt,
                             LocalDateTime deletedAt,
                             Long createdBy,
                             Long updatedBy,
                             String token,
                             LocalDateTime expiresAt,
                             UserEntity user) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.token = token;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}