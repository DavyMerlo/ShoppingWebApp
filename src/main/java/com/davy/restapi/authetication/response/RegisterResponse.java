package com.davy.restapi.authetication.response;

import com.davy.restapi.user.dto.UserItems;
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

    @JsonProperty("confirmationResponse")
    private String confirmationResponse;

    @JsonProperty("user")
    private UserItems user;
}
