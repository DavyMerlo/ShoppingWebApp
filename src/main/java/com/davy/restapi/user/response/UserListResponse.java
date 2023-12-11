package com.davy.restapi.user.response;

import com.davy.restapi.user.dto.UserItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserListResponse {
    @JsonProperty("users")
    private List<UserItems> users;
    {
        users = new ArrayList<>();
    }
}
