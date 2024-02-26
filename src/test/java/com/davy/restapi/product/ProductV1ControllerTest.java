package com.davy.restapi.product;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite()
@SuiteDisplayName("My Test Suite")
@SelectClasses({
        ProductByIdTest.class,
        ProductPaginationTest.class,
        ProductSaveTest.class,
        ProductUpdateTest.class})
public class ProductV1ControllerTest {
}
