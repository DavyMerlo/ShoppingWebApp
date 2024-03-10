package com.davy.restapi.product.controller.testsuite;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.test.annotation.DirtiesContext;

import static com.davy.restapi.product.data.ProductFieldProvider.expectedProductV1Fields;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductByIdTest extends TestContainer {

    @DisplayName("Fetch product with ID: 1")
    @Test
    @Order(1)
    public void shouldFetchProductById() throws Exception {
        long id = 1L;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "product", expectedProductV1Fields());
    }

    @DisplayName("Return 404 for non-existing product")
    @Test
    @Order(2)
    public void shouldReturn404NonExistingProduct() throws Exception {
        long nonExistingId = 9999L;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products/" + nonExistingId, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,404);
    }
}
