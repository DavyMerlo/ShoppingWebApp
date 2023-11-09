package com.davy.restapi.user.dto;

import com.davy.restapi.address.dto.AddressItems;

public record UserAddressItems(
        Long id,
        String firstName,
        String lastName,
        AddressItems address
) {
}
