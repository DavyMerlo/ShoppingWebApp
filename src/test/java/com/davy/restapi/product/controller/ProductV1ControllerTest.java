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
public class ProductV1ControllerTest extends TestContainer {

    @Autowired
    private TestRestTemplate products;

    @Autowired
    private TestRestTemplate product;

    @LocalServerPort
    private int port;

    @Test
    public void shouldFetchAllProductsPaginated() throws Exception {
        String responseBody = products
                .getForObject("http://localhost:" + port + "/api/v1/products", String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 8);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
    }

    @Test
    public void shouldFetchProductById() throws Exception {
        long id = 1L;
        String responseBody = product
                .getForObject("http://localhost:" + port + "/api/v1/products/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "product", expectedProductFields());
    }

    @Test
    public void shouldReturn404NonExistingProduct() throws Exception {
        long nonExistingId = 9999L;
        String responseBody = product
                .getForObject("http://localhost:" + port + "/api/v1/products/" + nonExistingId, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,404);
    }

    private List<String> expectedProductFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("name");
        fields.add("imageUrl");
        fields.add("description");
        fields.add("purchasePrice");
        fields.add("sellingPrice");
        fields.add("vat");
        fields.add("category");
        fields.add("inventory");
        fields.add("sellingPrice");
        return fields;
    }
}
