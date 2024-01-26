package com.davy.restapi.user.dto;

import com.davy.restapi.card.dto.CardDetails;

public record UserCardItems(
        Long id,
        String firstName,
        String lastName,
        CardDetails card
) {
}
