package com.davy.restapi.shared.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.davy.restapi.product.data.ProductFieldProvider.getObjectMetaDataFields;
import static com.davy.restapi.product.data.ProductFieldProvider.getResponseFields;
import static org.junit.jupiter.api.Assertions.*;

public class TestAssertionUtils {

    public static void assertResponseHasExpectedStatusCode(JSONObject response,
                                                           Integer statusCode)
            throws JSONException {
        assertEquals(statusCode, response.get("statusCode"));
    }

    public static void assertListResponseHasExpectedFields(JSONObject response,
                                                            String objectName,
                                                            List<String> expectedFields)
            throws JSONException {
        for(var field: getResponseFields()){
            assertTrue(response.has(field));
        }

        JSONObject result = response.getJSONObject("result");
        assertTrue(result.has(objectName));
        JSONArray array = result.getJSONArray(objectName);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            assertArrayHasExpectedFields(object, expectedFields);
        }
    }

    public static void assertResultHasExpectedMetaDataFields(JSONObject response)
            throws JSONException {
        JSONObject result = response.getJSONObject("result");
        for(var field: getObjectMetaDataFields()){
            assertTrue(result.has(field));
        }
    }

    public static void assertListResponseHasExpectedSize(JSONObject response,
                                                           String objectName,
                                                           int size)
            throws JSONException {
        JSONObject result = response.getJSONObject("result");
        assertTrue(result.has(objectName));
        JSONArray array = result.getJSONArray(objectName);
        assertEquals(size, array.length());
    }

    public static void assertArrayContains(JSONObject response,
                                           String arrayName,
                                           String fieldName,
                                           String value)
            throws JSONException {
        var array = getArray(response, arrayName);
        boolean found = false;
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.getString(fieldName).equals(value)) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Item '" + value + "' not found");
    }

    public static JSONArray getArray(JSONObject response, String arrayName)
            throws JSONException {
        JSONObject result = response.getJSONObject("result");
        return result.getJSONArray(arrayName);
    }

    public static void assertResponseHasExpectedFields(JSONObject response,
                                                       String objectName,
                                                       List<String> expectedFields)
            throws JSONException {
        for(var field: getResponseFields()){
            assertTrue(response.has(field));
        }
        JSONObject result = response.getJSONObject("result");
        assertTrue(result.has(objectName));
        JSONObject object = result.getJSONObject(objectName);
        assertObjectHasExpectedFields(object, expectedFields);
    }

    private static void assertObjectHasExpectedFields(JSONObject object, List<String> expectedFields)
            throws JSONException{
        for (String field : expectedFields) {
            assertTrue(object.has(field));
        }
    }

    private static void assertArrayHasExpectedFields(JSONObject object, List<String> expectedFields)
            throws JSONException {
        for (String field : expectedFields) {
            assertTrue(object.has(field));
        }
    }

    public static void assertArrayHasExpectedSize(JSONObject response,
                                                   String objectName,
                                                   Integer expectedSize)
            throws JSONException {
        JSONObject result = response.optJSONObject("result");
        JSONArray array = result.optJSONArray(objectName);
        int actualSize = array.length();
        assertEquals((int) expectedSize, actualSize);
    }
}
