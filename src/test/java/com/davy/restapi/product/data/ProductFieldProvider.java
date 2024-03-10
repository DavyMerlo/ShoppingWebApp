package com.davy.restapi.product.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductFieldProvider {

    public static List<String> getObjectMetaDataFields() {
        return Arrays.asList(
                "next",
                "previous",
                "count",
                "totalPages",
                "pageSize",
                "currentPage"
        );
    }

    public static List<String> getResponseFields(){
        return Arrays.asList(
                "result",
                "successful",
                "statusCode"
        );
    }

    public static List<String> expectedProductV2Fields() {
        return Arrays.asList(
                "id",
                "name"
        );
    }

    public static List<String> expectedProductV1Fields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("name");
        fields.add("imageUrl");
        fields.add("description");
        fields.add("purchasePrice");
        fields.add("sellingPrice");
        fields.add("vat");
        fields.add("category");
        fields.add("inventory");
        fields.add("sellingPrice");
        return fields;
    }
}
