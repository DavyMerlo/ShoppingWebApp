package com.davy.restapi.address.controller.testsuite;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertion;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Random;

import static com.davy.restapi.address.data.AddressFieldProvider.getAddressFields;

@DisplayName("Fetch address with a random ID and try to fetch with a non existing ID")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AddressByIdTest extends TestContainer {

    @Autowired
    private TestAssertion testAssertion;

    Random random = new Random();

    @DisplayName("Fetch address with a random ID")
    @Test
    @Order(1)
    public void shouldFetchAddressById() throws Exception {
        int min = 1;
        int max = 1;
        int randomId = random.nextInt(max) + min;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/addresses/" + randomId, String.class);
        testAssertion.provideResponse(responseBody);
        testAssertion.objectResponseHasExpectedFields("address", getAddressFields());
        testAssertion.responseHasExpectedStatusCode(200);
    }

    @DisplayName("Return 404 for non-existing product")
    @Test
    @Order(2)
    public void shouldReturn404NonExistingAddress() throws Exception {
        long nonExistingId = 9999L;
        String responseBody = restTemplate
                .getForObject("http://localhost:" + port + "/api/v1/addresses/" + nonExistingId, String.class);
        testAssertion.provideResponse(responseBody);
        testAssertion.responseHasExpectedStatusCode(404);
    }
}
