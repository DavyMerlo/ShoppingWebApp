package com.davy.restapi.user.response;

import com.davy.restapi.user.dto.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserListResponse {
    @JsonProperty("users")
    private List<User> users;
    {
        users = new ArrayList<>();
    }
}
