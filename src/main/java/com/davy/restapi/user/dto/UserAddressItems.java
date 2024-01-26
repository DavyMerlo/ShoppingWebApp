package com.davy.restapi.user.dto;

import com.davy.restapi.address.dto.Address;

public record UserAddressItems(
        Long id,
        String firstName,
        String lastName,
        Address address
) {
}
