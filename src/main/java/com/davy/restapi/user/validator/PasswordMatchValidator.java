package com.davy.restapi.user.validator;

import com.davy.restapi.user.request.ChangePasswordRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PasswordMatchValidator implements ConstraintValidator<CheckPasswordsMatch, Object> {

    @Override
    public void initialize(CheckPasswordsMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {

        if (obj instanceof ChangePasswordRequest) {
            ChangePasswordRequest request = (ChangePasswordRequest) obj;
            String newPassword = request.getNewPassword();
            String confirmationPassword = request.getConfirmationPassword();
            if (newPassword != null && !newPassword.equals(confirmationPassword)) {
                addConstraintViolation(constraintValidatorContext);
                return false;
            }
        }
        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode("confirmationPassword")
                .addConstraintViolation();
    }
}
