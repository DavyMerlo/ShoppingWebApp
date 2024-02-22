package com.davy.restapi.address.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressDetailDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("street") String street,
        @JsonProperty("houseNumber") String houseNumber,
        @JsonProperty("busNumber") String busNumber,
        @JsonProperty("postalCode") String postalCode,
        @JsonProperty("localAuthority") String localAuthority) {
}