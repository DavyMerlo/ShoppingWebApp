package com.davy.restapi.user.response;

import com.davy.restapi.user.dto.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    @JsonProperty("user")
    private User user;
}
