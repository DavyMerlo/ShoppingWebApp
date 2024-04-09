package com.davy.restapi.user.dto;


import com.davy.restapi.address.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserInvoiceDTO (
        @JsonProperty("id") Long id,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("address") AddressDTO address
) {
}
