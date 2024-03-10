package com.davy.restapi.user.mapper;

import com.davy.restapi.card.dto.CardDetailDTO;
import com.davy.restapi.user.dto.UserCard;
import com.davy.restapi.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class userCardMapper implements Function<UserEntity, UserCard> {

    @Override
    public UserCard apply(UserEntity user) {
        return new UserCard(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                new CardDetailDTO(
                        user.getCustomerCard().getId(),
                        user.getCustomerCard().getNumber(),
                        user.getCustomerCard().getPoints()
                )
        );
    }
}
