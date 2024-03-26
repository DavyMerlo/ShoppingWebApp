package com.davy.restapi.product.controller.testsuite;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertion;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static com.davy.restapi.product.data.ProductDataProvider.getJsonObjectToSave;
import static com.davy.restapi.product.data.ProductFieldProvider.expectedProductV1Fields;

@DisplayName("Save new product and fetch the new product")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductSaveTest extends TestContainer {

    @Autowired
    private TestAssertion testAssertion;

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
        testAssertion.provideResponse(responseBody);
        testAssertion.objectResponseHasExpectedFields("product", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(201);
    }

    @DisplayName("Fetch saved product")
    @Test
    @Order(2)
    public void shouldFetchSavedProduct() throws Exception {
        long id = 12L;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        testAssertion.provideResponse(responseBody);
        testAssertion.objectResponseHasExpectedFields("product", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
    }
}
