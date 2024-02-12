package com.davy.restapi.shared.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
        assertTrue(response.has("result"));
        assertTrue(response.has("successful"));
        assertTrue(response.has("statusCode"));

        JSONObject result = response.getJSONObject("result");
        assertTrue(result.has(objectName));
        JSONArray array = result.getJSONArray(objectName);

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            assertArrayHasExpectedFields(object, expectedFields);
        }
    }

    public static void assertResponseHasExpectedFields(JSONObject response,
                                                       String objectName,
                                                       List<String> expectedFields)
            throws JSONException {
        assertTrue(response.has("result"));
        assertTrue(response.has("successful"));
        assertTrue(response.has("statusCode"));

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
        assertEquals(actualSize, (int) expectedSize);
    }
}
