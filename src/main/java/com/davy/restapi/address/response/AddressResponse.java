package com.davy.restapi.address.response;

import com.davy.restapi.address.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressResponse {

    @JsonProperty("address")
    public AddressDto address;
}
