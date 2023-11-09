package com.davy.restapi.address.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AddressItems {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("street")
    public String street;

    @JsonProperty("houseNumber")
    public String houseNumber;

    @JsonProperty("busNumber")
    public String busNumber;

    @JsonProperty("postalCode")
    public String postalCode;

    @JsonProperty("localAuthority")
    public String localAuthority;

    public AddressItems(Long id,
                        String street,
                        String houseNumber,
                        String busNumber,
                        String postalCode,
                        String localAuthority) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.busNumber = busNumber;
        this.postalCode = postalCode;
        this.localAuthority = localAuthority;
    }
}
