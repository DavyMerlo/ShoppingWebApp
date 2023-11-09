package com.davy.restapi.user.mapper;

import com.davy.restapi.card.dto.CardItems;
import com.davy.restapi.user.dto.UserCardItems;
import com.davy.restapi.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class UserCardItemsMapper implements Function<User, UserCardItems> {

    @Override
    public UserCardItems apply(User user) {
        return new UserCardItems(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                new CardItems(
                        user.getCustomerCard().getId(),
                        user.getCustomerCard().getNumber(),
                        user.getCustomerCard().getPoints()
                )
        );
    }
}
