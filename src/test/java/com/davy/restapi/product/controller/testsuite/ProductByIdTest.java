package com.davy.restapi.product.controller.testsuite;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertion;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Random;

import static com.davy.restapi.product.data.ProductFieldProvider.expectedProductV1Fields;

@DisplayName("Fetch product with a random ID and try to fetch with a non existing ID")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductByIdTest extends TestContainer {

    @Autowired
    private TestAssertion testAssertion;

    Random random = new Random();

    @DisplayName("Fetch product with a random ID")
    @Test
    @Order(1)
    public void shouldFetchProductById() throws Exception {
        int min = 1;
        int max = 11;
        int randomId = random.nextInt(max) + min;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products/" + randomId, String.class);
        testAssertion.provideResponse(responseBody);
        testAssertion.objectResponseHasExpectedFields("product", expectedProductV1Fields());
        testAssertion.responseHasExpectedStatusCode(200);
    }

    @DisplayName("Return 404 for non-existing product")
    @Test
    @Order(2)
    public void shouldReturn404NonExistingProduct() throws Exception {
        long nonExistingId = 9999L;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/products/" + nonExistingId, String.class);
        testAssertion.provideResponse(responseBody);
        testAssertion.responseHasExpectedStatusCode(404);
    }
}
