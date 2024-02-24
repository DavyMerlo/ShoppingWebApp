package com.davy.restapi.subcategory.data;

import java.util.ArrayList;
import java.util.List;


public class SubCategoryFieldProvider {

    public static List<String> getSubCategoryFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("name");
        return fields;
    }

    public static List<String> getSubCategoryDetailFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("name");
        fields.add("category");
        return fields;
    }
}
