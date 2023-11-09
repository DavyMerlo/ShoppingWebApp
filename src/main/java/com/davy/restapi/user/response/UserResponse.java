package com.davy.restapi.user.response;

import com.davy.restapi.user.dto.UserItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserResponse {

    @JsonProperty("user")
    private UserItems user;
}
