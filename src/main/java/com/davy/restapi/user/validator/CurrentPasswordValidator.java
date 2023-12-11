package com.davy.restapi.user.validator;

import com.davy.restapi.shared.validators.ContextProvider;
import com.davy.restapi.user.entity.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CurrentPasswordValidator implements ConstraintValidator<CheckCurrentPassword, String> {

    private PasswordEncoder passwordEncoder;

    @Override
    public void initialize(CheckCurrentPassword constraintAnnotation) {
        this.passwordEncoder = (PasswordEncoder) ContextProvider.getBean(PasswordEncoder.class);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if(!s.isEmpty()){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var user = (User) ((UsernamePasswordAuthenticationToken) authentication).getPrincipal();
            return passwordEncoder.matches(s, user.getPassword());
        }
        return true;
    }
}
