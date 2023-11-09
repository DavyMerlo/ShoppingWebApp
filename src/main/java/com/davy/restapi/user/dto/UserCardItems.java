package com.davy.restapi.user.dto;

import com.davy.restapi.card.dto.CardItems;

public record UserCardItems(
        Long id,
        String firstName,
        String lastName,
        CardItems card
) {
}
