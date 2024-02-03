package com.davy.restapi.user.mapper;

import com.davy.restapi.card.dto.CardDetail;
import com.davy.restapi.user.dto.UserCard;
import com.davy.restapi.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class userCardMapper implements Function<User, UserCard> {

    @Override
    public UserCard apply(User user) {
        return new UserCard(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                new CardDetail(
                        user.getCustomerCard().getId(),
                        user.getCustomerCard().getNumber(),
                        user.getCustomerCard().getPoints()
                )
        );
    }
}
