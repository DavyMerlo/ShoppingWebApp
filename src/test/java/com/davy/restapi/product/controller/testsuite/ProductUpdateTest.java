package com.davy.restapi.product.controller.testsuite;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertion;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static com.davy.restapi.product.data.ProductDataProvider.getJsonObjectToUpdate;
import static com.davy.restapi.product.data.ProductFieldProvider.expectedProductV1Fields;

@DisplayName("Update product with ID: 5 and fetch this product")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductUpdateTest extends TestContainer {

    @Autowired
    private TestAssertion testAssertion;

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
        testAssertion.provideResponse(responseBody);
        testAssertion.objectResponseHasExpectedFields("product", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
    }

    @DisplayName("Fetch updated product")
    @Test
    @Order(2)
    public void shouldFetchUpdatedProduct() throws Exception {
        long id = 5L;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products/" + id, String.class);
        testAssertion.provideResponse(responseBody);
        testAssertion.objectResponseHasExpectedFields("product", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
    }
}
