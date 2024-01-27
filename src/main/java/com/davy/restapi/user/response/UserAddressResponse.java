package com.davy.restapi.user.response;

import com.davy.restapi.user.dto.UserAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAddressResponse {

    @JsonProperty("user")
    private UserAddress user;
}
