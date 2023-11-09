package com.davy.restapi.user.response;

import com.davy.restapi.user.dto.UserAddressItems;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAddressResponse {

    @JsonProperty("user")
    public UserAddressItems user;
}
