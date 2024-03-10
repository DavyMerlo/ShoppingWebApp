package com.davy.restapi.product.controller;

import com.davy.restapi.product.controller.testsuite.ProductByIdTest;
import com.davy.restapi.product.controller.testsuite.ProductPaginationTest;
import com.davy.restapi.product.controller.testsuite.ProductSaveTest;
import com.davy.restapi.product.controller.testsuite.ProductUpdateTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ProductByIdTest.class,
        ProductPaginationTest.class,
        ProductSaveTest.class,
        ProductUpdateTest.class})
public class ProductV1ControllerTest {
}
