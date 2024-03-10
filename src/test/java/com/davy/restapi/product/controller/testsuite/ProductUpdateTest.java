package com.davy.restapi.product.controller.testsuite;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductUpdateTest extends TestContainer {

    @DisplayName("Update product with ID: 5")
    @Test
    @Order(1)
    public void shouldUpdateProduct() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        JSONObject requestBody = getJsonObjectToUpdate();

        long productId = 5L;
        String url = "http://localhost:" + port + "/api/v1/products/" + productId;
        ResponseEntity<String> responseEntity = restTemplate.exchange(
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

    @DisplayName("Fetch updated product")
    @Test
    @Order(2)
    public void shouldFetchUpdatedProduct() throws Exception {
        long id = 5L;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "product", expectedProductFields());
    }

    @NotNull
    private static JSONObject getJsonObjectToUpdate() throws JSONException {
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
