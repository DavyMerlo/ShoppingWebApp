package com.davy.restapi.customercard.data;

import java.util.ArrayList;
import java.util.List;

public class CustomerCardFieldProvider {

    public static List<String> getCustomerCardFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("number");
        return fields;
    }

    public static List<String> getCustomerCardDetailFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("number");
        fields.add("points");
        return fields;
    }
}
