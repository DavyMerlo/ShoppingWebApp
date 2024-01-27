package com.davy.restapi.user.dto;

import com.davy.restapi.card.dto.CardDetails;

public record UserCard(
        Long id,
        String firstName,
        String lastName,
        CardDetails card
) {
}
