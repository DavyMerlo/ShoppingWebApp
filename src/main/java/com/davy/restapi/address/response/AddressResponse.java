package com.davy.restapi.address.response;

import com.davy.restapi.address.dto.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    @JsonProperty("address")
    public Address address;
}
