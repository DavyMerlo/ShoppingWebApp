package com.davy.restapi.user.mapper;

import com.davy.restapi.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserItemsMapper implements Function<UserEntity, com.davy.restapi.user.dto.User> {
    @Override
    public com.davy.restapi.user.dto.User apply(UserEntity user) {
        return new com.davy.restapi.user.dto.User(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getRole()
        );
    }
}
