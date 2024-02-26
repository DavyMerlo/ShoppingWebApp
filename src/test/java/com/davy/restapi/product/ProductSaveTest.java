package com.davy.restapi.product;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductSaveTest extends TestContainer {

    @DisplayName("Save new product")
    @Test
    @Order(11)
    public void shouldSaveProduct() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String url = "http://localhost:" + port + "/api/v1/products";
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(
                        url,
                        new HttpEntity<>(getJsonObjectToSave().toString(), headers),
                        String.class
                );
        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 201);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "product", expectedProductFields());
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

    @NotNull
    private static JSONObject getJsonObjectToSave() throws JSONException {
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
        return requestBody;
    }
}
