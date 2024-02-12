package com.davy.restapi.product.controller;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductV2ControllerTest extends TestContainer {

    @Autowired
    private TestRestTemplate products;

    @LocalServerPort
    private int port;

    @Test
    public void shouldFetchAllProducts() throws Exception {
        String responseBody = products
                .getForObject("http://localhost:" + port + "/api/v2/products", String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 11);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
    }

    private List<String> expectedProductFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("name");
        return fields;
    }
}
