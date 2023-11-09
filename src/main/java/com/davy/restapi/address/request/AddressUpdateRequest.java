package com.davy.restapi.address.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AddressUpdateRequest {

    @NotNull(message = "Street should be not be empty")
    @NotEmpty(message = "Street should be not be empty")
    @Pattern(regexp="^[A-Za-z]*$",message = "Street should contains only letters")
    @Size(min = 1, max = 35, message = "Street should be between 1 and 35 letters long")
    private String street;

    @NotNull(message = "Housenumber should be not be empty")
    @NotEmpty(message = "Housenumber should be not be empty")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Housenumber should be a numeric value")
    @Size(min = 1, max = 5, message = "Housenumber should be between 1 and 35 letters long")
    private String houseNumber;

    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Busnumber should be a numeric value")
    @Size(min = 1, max = 5)
    private String busNumber;

    @NotNull(message = "Postalcode should be not be empty")
    @NotEmpty(message = "Postalcode should be not be empty")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Postalcode should be a numeric value")
    @Size(min = 1, max = 4, message = "Postalcode should be between 1 and 4 digits long")
    private String postalCode;

    @NotNull(message = "Localauthority should be not be empty")
    @NotEmpty(message = "Localauthority should be not be empty")
    @Pattern(regexp="^[A-Za-z]*$",message = "Local authority should contains only letters")
    @Size(min = 1, max = 35, message = "Localauthority should be between 1 and 35 letters long")
    private String localAuthority;
}
