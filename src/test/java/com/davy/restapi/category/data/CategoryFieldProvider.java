package com.davy.restapi.category.data;

import java.util.ArrayList;
import java.util.List;

public class CategoryFieldProvider {

    public static List<String> getCategoryFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("name");
        return fields;
    }

    public static List<String> getCategoryDetailFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("name");
        fields.add("subCategories");
        return fields;
    }
}
