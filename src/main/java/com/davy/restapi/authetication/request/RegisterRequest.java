package com.davy.restapi.authetication.request;

import com.davy.restapi.user.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

//    @NotEmpty(message = "password should not be empty")
//    @NotNull(message = "password should not be empty")
    private String firstName;


//    @NotEmpty(message = "password should not be empty")
//    @NotNull(message = "password should not be empty")
    private String lastName;


    @NotEmpty(message = "password should not be empty")
    @NotNull(message = "password should not be empty")
//    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
//            flags = Pattern.Flag.CASE_INSENSITIVE,
//            message = "Email-address is not valid")
    private String email;


    @NotEmpty(message = "Password should not be empty")
    @NotNull(message = "Password should not be empty")
//    @Size(min=8, message = "Password must be at least 8 characters long")
    private String password;


//    @NotEmpty(message = "Role should not be empty")
//    @NotNull(message = "Role should not be empty")
    private Role role;


    @NotEmpty(message = "Street should not be empty")
    @NotNull(message = "Street should not be empty")
//    @Pattern(regexp="^[A-Za-z]*$",message = "Street should contains only letters")
//    @Size(min = 1, max = 35, message = "Street should be between 1 and 35 letters long")
    private String street;


    @NotEmpty(message = "Housenumber should not be empty")
    @NotNull(message = "housenumber should not be empty")
//    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Housenumber should be a numeric value")
//    @Size(min = 1, max = 5, message = "Housenumber should be between 1 and 35 letters long")
    private String houseNumber;


//    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Busnumber should be a numeric value")
//    @Size(min = 1, max = 5)
    private String busNumber;


    @NotEmpty(message = "Postalcode should not be empty")
    @NotNull(message = "Postalcode should not be empty")
//    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Postalcode should be a numeric value")
//    @Size(min = 1, max = 4, message = "Postalcode should be between 1 and 4 digits long")
    private String postalCode;


    @NotEmpty(message = "Localauthority should not be empty")
    @NotNull(message = "Localauthority should not be empty")
//    @Pattern(regexp="^[A-Za-z]*$",message = "Localauthority should contains only letters")
//    @Size(min = 1, max = 35, message = "Localauthority should be between 1 and 35 letters long")
    private String localAuthority;
}
