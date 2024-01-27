package com.davy.restapi.user.mapper;

import com.davy.restapi.user.dto.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserItemsMapper implements Function<com.davy.restapi.user.entity.User, User> {
    @Override
    public User apply(com.davy.restapi.user.entity.User user) {
        return new User(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getRole()
        );
    }
}
