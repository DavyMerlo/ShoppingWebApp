package com.davy.restapi.user.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
public @interface CheckPasswordsMatch {

    String message() default "Confirmation password does not match new password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
