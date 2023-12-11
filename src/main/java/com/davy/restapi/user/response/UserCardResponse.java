package com.davy.restapi.user.response;

import com.davy.restapi.user.dto.UserCardItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCardResponse {

    @JsonProperty("user")
    private UserCardItems user;
}
