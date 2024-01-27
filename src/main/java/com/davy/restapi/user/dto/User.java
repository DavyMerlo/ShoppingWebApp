package com.davy.restapi.user.dto;

import com.davy.restapi.user.enums.Role;

public record User(
        Long id,
        String userName,
        String firstName,
        String lastName,
        String email,
        Role role
) {
}
