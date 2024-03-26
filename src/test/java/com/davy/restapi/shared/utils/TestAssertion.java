package com.davy.restapi.shared.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public interface TestAssertion {

    JSONObject provideResponse(String responseBody)
            throws JSONException;

    void listResponseHasExpectedFields(String objName, List<String> expectedFields)
            throws JSONException;

    void objectResponseHasExpectedFields(String objectName, List<String> expectedFields)
            throws JSONException;

    void responseHasExpectedStatusCode(int statusCode)
            throws JSONException;

    void arrayHasExpectedSize(String objectName, int expectedSize)
            throws JSONException;

    void responseHasMetaDataFields(String objectName)
            throws JSONException;

    void assertArrayContains(String arrayName,
                             String fieldName,
                             String value)
            throws JSONException;
}
