package com.davy.restapi.user.request;

import com.davy.restapi.user.validator.CheckCurrentPassword;
import com.davy.restapi.user.validator.CheckPasswordsMatch;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@CheckPasswordsMatch()
public class ChangePasswordRequest {

    @NotEmpty(message = "Current password should not be empty")
    @NotNull(message = "Current password should not be empty")
    @CheckCurrentPassword()
    private String currentPassword;

    @NotEmpty(message = "New password should not be empty")
    @NotNull(message = "New password should not be empty")
    private String newPassword;

    @NotEmpty(message = "Confirmation password should not be empty")
    @NotNull(message = "Confirmation password should not be empty")
    private String confirmationPassword;
}
