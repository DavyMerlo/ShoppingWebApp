package com.davy.restapi.user.dto;

import com.davy.restapi.card.dto.CardDetail;

public record UserCard(
        Long id,
        String firstName,
        String lastName,
        CardDetail card
) {
}
