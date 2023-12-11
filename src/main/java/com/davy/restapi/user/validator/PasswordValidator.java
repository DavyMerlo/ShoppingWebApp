package com.davy.restapi.user.validator;

import com.davy.restapi.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordValidator implements IPasswordValidator {

    private final PasswordEncoder passwordEncoder;

    public boolean isValidPassword(String password) {
        if (passwordEncoder != null) {
            // Controleer of de passwordEncoder niet null is voordat je deze gebruikt
            String encodedPassword = passwordEncoder.encode(password);
            // Voer andere validatielogica uit
            return true; // of false, afhankelijk van je logica
        }
        return false; // of throw exception, afhankelijk van je behoefte
    }
}
