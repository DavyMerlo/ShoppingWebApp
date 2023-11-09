package com.davy.restapi.user.response;

import com.davy.restapi.user.dto.UserItems;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {

    @JsonProperty("user")
    public UserItems user;
}
