package com.davy.restapi.authetication.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Email
    @NotEmpty(message = "Email-address should not be empty")
    @NotNull(message = "Email-address should not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
    message = "Email-address is not valid")
    private String email;


    @NotEmpty(message = "Password should not be empty")
    @NotNull(message = "Password should not be empty")
    @Size(min=8, message = "Password must be at least 8 characters long")
    private String password;
}
