package com.davy.restapi.address.controller;

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
public class AddressControllerTest extends TestContainer {

    @Autowired
    private TestRestTemplate addresses;

    @Autowired
    private TestRestTemplate address;

    @LocalServerPort
    private int port;

    @Test
    public void shouldFetchAllAddresses() throws Exception {
        String responseBody = addresses
                .getForObject("http://localhost:" + port + "/api/v1/addresses", String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "addresses", expectedAddressFields());
    }

    @Test
    public void shouldFetchAddressById() throws Exception {
        long id = 1L;
        String responseBody = address
                .getForObject("http://localhost:" + port + "/api/v1/addresses/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "address", expectedAddressFields());

    }

    @Test
    public void shouldReturn404NonExistingProduct() throws Exception {
        long nonExistingId = 9999L;
        String responseBody = address
                .getForObject("http://localhost:" + port + "/api/v1/addresses/" + nonExistingId, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,404);
    }

    private List<String> expectedAddressFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("street");
        fields.add("houseNumber");
        fields.add("busNumber");
        fields.add("postalCode");
        fields.add("localAuthority");
        return fields;
    }
}
