package com.davy.restapi.user.mapper;

import com.davy.restapi.user.entity.User;
import com.davy.restapi.user.dto.UserItems;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserItemsMapper implements Function<User, UserItems> {
    @Override
    public UserItems apply(User user) {
        return new UserItems(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getRole()
        );
    }
}
