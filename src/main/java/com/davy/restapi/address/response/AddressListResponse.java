package com.davy.restapi.address.response;

import com.davy.restapi.address.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddressListResponse {
    @JsonProperty("addresses")
    private List<AddressDTO> addresses;
    {
        addresses = new ArrayList<>();
    }
}
