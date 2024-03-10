package com.davy.restapi.shared.utils;

import org.json.JSONException;
import org.json.JSONObject;

public interface JSONToObject<T> {
    T mapJSONResponseToObject(JSONObject response) throws JSONException;
}
