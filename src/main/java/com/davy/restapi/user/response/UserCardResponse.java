package com.davy.restapi.user.response;

import com.davy.restapi.user.dto.UserCardItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserCardResponse {

    @JsonProperty("user")
    private UserCardItems user;
}
