package com.davy.restapi.shared.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public interface TestAssertion {

    void provideResponse(String responseBody)
            throws JSONException;

    void responseHasExpectedFields(String objName, List<String> expectedFields)
            throws JSONException;

    void responseHasExpectedStatusCode(int statusCode)
            throws JSONException;

    void arrayHasExpectedSize(String objectName, int expectedSize)
            throws JSONException;
}
