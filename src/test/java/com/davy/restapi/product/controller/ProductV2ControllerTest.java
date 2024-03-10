package com.davy.restapi.product.controller;

import com.davy.restapi.product.controller.testsuite.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ProductFetchAllTest.class
})
public class ProductV2ControllerTest {
}
