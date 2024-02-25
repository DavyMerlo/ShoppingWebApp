package com.davy.restapi.product;


import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

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

//    @Test
//    public void shouldFetchAllProductsPaginated() throws Exception {
//        String responseBody = products
//                .getForObject("http://localhost:" + port + "/api/v1/products", String.class);
//        JSONObject response = new JSONObject(responseBody);
//        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
//        TestAssertionUtils.assertArrayHasExpectedSize(response, "products", 8);
//        TestAssertionUtils.assertListResponseHasExpectedFields(response, "products", expectedProductFields());
//    }

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
    public void shouldSaveProduct() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Test Product Saved");
        requestBody.put("description", "Test Product Description");
        requestBody.put("imageUrl", "https://example.com/image.jpg");
        requestBody.put("purchasePrice", 49.99);
        requestBody.put("sellingPrice", 59.99);
        requestBody.put("vat", 0);
        requestBody.put("quantity", 100);
        requestBody.put("categoryId", 1);
        requestBody.put("subCategoryId", 2);

        String url = "http://localhost:" + port + "/api/v1/products";
        ResponseEntity<String> responseEntity = product
                .postForEntity(
                        url,
                        new HttpEntity<>(requestBody.toString(), headers),
                        String.class
                );
        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 201);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "product", expectedProductFields());
    }

    @Test
    public void shouldUpdateProduct() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        JSONObject requestBody = getJsonObject();

        long productId = 5L;
        String url = "http://localhost:" + port + "/api/v1/products/" + productId;
        ResponseEntity<String> responseEntity = product.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(requestBody.toString(), headers),
                String.class
        );
        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
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

    @NotNull
    private static JSONObject getJsonObject() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Test Product Updated");
        requestBody.put("description", "Test Product Description Updated");
        requestBody.put("imageUrl", "https://example.com/image.jpg");
        requestBody.put("purchasePrice", 49.99);
        requestBody.put("sellingPrice", 59.99);
        requestBody.put("vat", 0);
        requestBody.put("quantity", 100);
        requestBody.put("categoryId", 1);
        requestBody.put("subCategoryId", 2);
        return requestBody;
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
