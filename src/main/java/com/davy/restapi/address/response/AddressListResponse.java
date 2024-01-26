package com.davy.restapi.address.response;

import com.davy.restapi.address.dto.AddressItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddressListResponse {

    @JsonProperty("addresses")
    private List<AddressItems> addresses;
    {
        addresses = new ArrayList<>();
    }
}
