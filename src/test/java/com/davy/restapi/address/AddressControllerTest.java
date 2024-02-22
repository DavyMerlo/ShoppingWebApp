package com.davy.restapi.address;

import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.utils.TestAssertionUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Order;
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
public class AddressControllerTest extends TestContainer {

    @Autowired
    private TestRestTemplate addresses;

    @Autowired
    private TestRestTemplate address;

    @LocalServerPort
    private int port;

    @Test
    @Order(1)
    public void shouldFetchAllAddresses() throws JSONException {
        String responseBody = addresses
                .getForObject("http://localhost:" + port + "/api/v1/addresses", String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertListResponseHasExpectedFields(response, "addresses", expAddressFields());
        TestAssertionUtils.assertListResponseHasExpectedSize(response, "addresses", 1);
    }

    @Test
    @Order(2)
    public void shouldFetchAddressById() throws JSONException {
        long id = 1L;
        String responseBody = address
                .getForObject("http://localhost:" + port + "/api/v1/addresses/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "address", expAddressFields());
        TestAssertionUtils.assertResponseHasExpectedValues(response,
                "address",
                expAddressFields(),
                expAddressFieldValues()
        );
    }

    @Test
    @Order(3)
    public void shouldSaveAddress() throws JSONException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("street", "Teststreet Saved");
        requestBody.put("houseNumber", "50");
        requestBody.put("busNumber", "1");
        requestBody.put("postalCode", "3630");
        requestBody.put("localAuthority", "TestCity");

        String url = "http://localhost:" + port + "/api/v1/addresses";
        ResponseEntity<String> responseEntity = address
                .postForEntity(url, new HttpEntity<>(requestBody.toString(), headers), String.class);

        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);

        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 201);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "address", expAddressFields());
    }

    @Test
    @Order(4)
    public void shouldFetchSavedAddressById() throws JSONException {
        long id = 2L;
        String responseBody = address
                .getForObject("http://localhost:" + port + "/api/v1/addresses/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "address", expAddressFields());
        TestAssertionUtils.assertResponseHasExpectedValues(response,
                "address",
                expAddressFields(),
                expSavedAddressFieldValues()
        );
    }

    @Test
    @Order(5)
    public void shouldUpdateAddress() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("street", "Teststreet Updated");
        requestBody.put("houseNumber", "50");
        requestBody.put("busNumber", "1");
        requestBody.put("postalCode", "3630");
        requestBody.put("localAuthority", "TestCity");

        long addressId = 1L;
        String url = "http://localhost:" + port + "/api/v1/addresses/" + addressId;
        ResponseEntity<String> responseEntity = address.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(requestBody.toString(), headers),
                String.class
        );

        String responseBody = responseEntity.getBody();
        JSONObject response = new JSONObject(responseBody);

        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "address", expAddressFields());
    }

    @Test
    @Order(6)
    public void shouldFetchUpdatedAddressById() throws JSONException {
        long id = 1L;
        String responseBody = address
                .getForObject("http://localhost:" + port + "/api/v1/addresses/" + id, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response, 200);
        TestAssertionUtils.assertResponseHasExpectedFields(response, "address", expAddressFields());
        TestAssertionUtils.assertResponseHasExpectedValues(response,
                "address",
                expAddressFields(),
                expUpdatedAddressFieldValues()
        );
    }

    @Test
    @Order(7)
    public void shouldReturn404NonExistingProduct() throws JSONException {
        long nonExistingId = 9999L;
        String responseBody = address
                .getForObject("http://localhost:" + port + "/api/v1/addresses/" + nonExistingId, String.class);
        JSONObject response = new JSONObject(responseBody);
        TestAssertionUtils.assertResponseHasExpectedStatusCode(response,404);
    }

    private List<String> expAddressFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("street");
        fields.add("houseNumber");
        fields.add("busNumber");
        fields.add("postalCode");
        fields.add("localAuthority");
        return fields;
    }

    private List<Object> expAddressFieldValues() {
        List<Object> fields = new ArrayList<>();
        fields.add(1);
        fields.add("Rootstraat");
        fields.add("30");
        fields.add("1");
        fields.add("3630");
        fields.add("Maasmechelen");
        return fields;
    }

    private List<Object> expSavedAddressFieldValues() {
        List<Object> fields = new ArrayList<>();
        fields.add(2);
        fields.add("Teststreet Saved");
        fields.add("50");
        fields.add("1");
        fields.add("3630");
        fields.add("TestCity");
        return fields;
    }

    private List<Object> expUpdatedAddressFieldValues() {
        List<Object> fields = new ArrayList<>();
        fields.add(1);
        fields.add("Teststreet Updated");
        fields.add("50");
        fields.add("1");
        fields.add("3630");
        fields.add("TestCity");
        return fields;
    }
}
