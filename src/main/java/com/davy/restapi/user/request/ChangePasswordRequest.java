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

    @CheckCurrentPassword()
    @NotEmpty(message = "current password should not be empty")
    @NotNull(message = "current password should not be empty")
    private String currentPassword;

    @NotEmpty(message = "new password should not be empty")
    @NotNull(message = "new password should not be empty")
    private String newPassword;

    @NotEmpty(message = "confirmation password should not be empty")
    @NotNull(message = "confirmation password should not be empty")
    private String confirmationPassword;
}
