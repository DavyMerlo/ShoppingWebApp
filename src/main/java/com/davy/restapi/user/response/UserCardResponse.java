package com.davy.restapi.user.response;

import com.davy.restapi.user.dto.UserCardItems;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCardResponse {

    @JsonProperty("user")
    public UserCardItems user;
}
