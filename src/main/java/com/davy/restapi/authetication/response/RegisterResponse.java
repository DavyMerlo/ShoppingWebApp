package com.davy.restapi.authetication.response;

import com.davy.restapi.user.dto.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {

    @JsonProperty("confirmationToken")
    private String confirmationToken;

    @JsonProperty("user")
    private User user;
}
