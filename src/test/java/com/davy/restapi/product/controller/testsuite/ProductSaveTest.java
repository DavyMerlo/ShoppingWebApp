package com.davy.restapi.product.controller.testsuite;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static com.davy.restapi.product.data.ProductDataProvider.getJsonObjectToSave;
import static com.davy.restapi.product.data.ProductFieldProvider.expectedProductV1Fields;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductSaveTest extends TestContainer {

    @DisplayName("Save new product")
    @Test
    @Order(1)
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
        TestAssertionUtils.assertResponseHasExpectedFields(response, "product", expectedProductV1Fields());
    }

    @DisplayName("Fetch saved product")
    @Test
    @Order(2)
    public void shouldFetchSavedProduct() throws Exception {
        long id = 12L;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "product", expectedProductV1Fields());
    }
}
