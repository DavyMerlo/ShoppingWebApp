package com.davy.restapi.shared.utils;

public interface ExpectedDataProvider<T> {

    T getObject();

    T getSavedObject();

    T getUpdatedObject();
}
