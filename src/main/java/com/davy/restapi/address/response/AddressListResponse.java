package com.davy.restapi.address.response;

import com.davy.restapi.address.dto.AddressItems;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AddressListResponse {

    @JsonProperty("addresses")
    public List<AddressItems> addresses;
    {
        addresses = new ArrayList<>();
    }
}
