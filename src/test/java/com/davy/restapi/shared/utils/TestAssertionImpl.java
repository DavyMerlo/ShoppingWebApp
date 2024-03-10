package com.davy.restapi.shared.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
public class TestAssertionImpl implements TestAssertion {

    private final String resultKey = "result";
    private JSONObject response;

    @Override
    public void provideResponse(String responseBody)
            throws JSONException {
        this.response = new JSONObject(responseBody);
    }

    @Override
    public void responseHasExpectedFields(String objectName, List<String> expectedFields)
            throws JSONException {
        JSONObject responseObj = response;
        JSONObject resultObj = responseObj.getJSONObject(resultKey);
        JSONArray array = resultObj.getJSONArray(objectName);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            for(String field: expectedFields){
                assertTrue(object.has(field));
            }
        }
    }

    @Override
    public void arrayHasExpectedSize(String objectName, int expectedSize)
            throws JSONException {
        JSONObject responseObj = response;
        JSONObject resultObj = responseObj.getJSONObject(resultKey);
        JSONArray array = resultObj.getJSONArray(objectName);
        assertEquals(expectedSize, array.length(), "Array size is not as expected");
    }

    @Override
    public void responseHasExpectedStatusCode(int statusCode)
            throws JSONException {
        assertEquals(statusCode, response.get("statusCode"), "StatusCode not correct");
    }
}
