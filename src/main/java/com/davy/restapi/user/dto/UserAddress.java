package com.davy.restapi.user.dto;

import com.davy.restapi.address.dto.AddressDTO;

public record UserAddress(
        Long id,
        String firstName,
        String lastName,
        AddressDTO addressDto
) {
}
