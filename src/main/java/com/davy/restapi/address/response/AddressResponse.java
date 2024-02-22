package com.davy.restapi.address.response;

import com.davy.restapi.address.dto.AddressDetailDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressResponse {

    @JsonProperty("address")
    public AddressDetailDTO address;
}
