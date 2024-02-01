package com.davy.restapi.address.response;

import com.davy.restapi.address.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddressListResponse {

    @JsonProperty("addresses")
    private List<AddressDto> addresses;
    {
        addresses = new ArrayList<>();
    }
}
