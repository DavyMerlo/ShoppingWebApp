package com.davy.restapi.user.response;

import com.davy.restapi.user.dto.UserAddressItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserAddressResponse {

    @JsonProperty("user")
    private UserAddressItems user;
}
