package com.davy.restapi.user.dto;

import com.davy.restapi.card.dto.CardDetailDTO;

public record UserCard(
        Long id,
        String firstName,
        String lastName,
        CardDetailDTO card
) {
}
