package com.davy.restapi.shared.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.davy.restapi.product.data.ProductFieldProvider.getObjectMetaDataFields;
import static org.junit.jupiter.api.Assertions.*;

@Component
public class TestAssertionImpl implements TestAssertion {

    private final String resultKey = "result";
    private JSONObject response;

    @Override
    public JSONObject provideResponse(String responseBody)
            throws JSONException {
        this.response = new JSONObject(responseBody);
        return response;
    }

    @Override
    public void listResponseHasExpectedFields(String objectName, List<String> expectedFields)
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
    public void objectResponseHasExpectedFields(String objectName, List<String> expectedFields)
            throws JSONException {
        JSONObject responseObj = response;
        JSONObject resultObj = responseObj.getJSONObject(resultKey);
        JSONObject object = resultObj.getJSONObject(objectName);
        for(String field: expectedFields){
            assertTrue(object.has(field));
        }
    }

    @Override
    public void responseHasMetaDataFields(String objectName)
            throws JSONException {
        JSONObject responseObject = response;
        JSONObject resultObject = responseObject.getJSONObject(resultKey);
        for(var field: getObjectMetaDataFields()){
            assertTrue(resultObject.has(field));
        }
    }

    @Override
    public void assertArrayContains(String arrayName, String fieldName, String value)
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

    private JSONArray getArray(JSONObject response, String arrayName)
            throws JSONException {
        JSONObject result = response.getJSONObject("result");
        return result.getJSONArray(arrayName);
    }
}
