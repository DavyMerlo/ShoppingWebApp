package com.davy.restapi.product.data;

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
}
