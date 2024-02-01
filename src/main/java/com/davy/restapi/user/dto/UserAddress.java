package com.davy.restapi.user.dto;

import com.davy.restapi.address.dto.AddressDto;

public record UserAddress(
        Long id,
        String firstName,
        String lastName,
        AddressDto addressDto
) {
}
