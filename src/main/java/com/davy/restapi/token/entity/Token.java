package com.davy.restapi.token.entity;

import com.davy.restapi.shared.entity.BaseEntity;
import com.davy.restapi.token.enums.TokenType;
import com.davy.restapi.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name="default_gen",
        sequenceName = "token_id_seq",
        allocationSize=1)
public class Token extends BaseEntity {

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}
