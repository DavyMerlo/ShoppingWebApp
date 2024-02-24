package com.davy.restapi.address.data;

import java.util.ArrayList;
import java.util.List;

public class AddressFieldProvider {

    public static List<String> getAddressFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("street");
        fields.add("houseNumber");
        fields.add("busNumber");
        fields.add("postalCode");
        fields.add("localAuthority");
        return fields;
    }
}
